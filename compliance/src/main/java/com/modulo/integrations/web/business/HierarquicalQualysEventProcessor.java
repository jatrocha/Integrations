package com.modulo.integrations.web.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.joda.time.LocalDate;

import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.persistence.model.QPCEventEntity;
import com.modulo.integrations.commons.qualys.converter.QPCEventEntityToEventConverter;
import com.modulo.riskmanager.client.adapter.AssetServiceAdapter;
import com.modulo.riskmanager.client.adapter.EventServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.AssetFilter;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.client.to.event.Event;
import com.modulo.riskmanager.enums.AssetType;

public class HierarquicalQualysEventProcessor extends MultiThreadedEventProcessor<QPCEventEntity> {

   private static final Integer WORKERS = Integer.valueOf(PropertiesUtil.getInstance().getString("NUMBER.OF.WORKERS"));

   @Resource
   private AssetBusiness assetBusiness;

   @Resource
   private AssetServiceAdapter assetServiceAdapter;

   @Resource
   private EventServiceAdapter eventServiceAdapter;

   @Resource
   private EventBusiness eventBusiness;

   @Resource
   private EntityManagerFactory factory;

   protected static List<QPCEventEntity> EVENTS;

   protected static Map<String, Asset> ASSETS;

   protected static final Map<String, Event> PARENT_EVENT = new HashMap<String, Event>(0);

   @Override
   public void process(List<QPCEventEntity> events) throws LackOfAuthorizationException {

      final String path = PropertiesUtil.getInstance().getString("QPC.Asset.Perimeter.Name");

      final List<Asset> assets = this.assetServiceAdapter.list(super.getAuth(), new AssetFilter(AssetType.TECNOLOGIA).withPerimeter(path));

      final Map<String, Asset> assetMaps = new HashMap<String, Asset>(assets.size());

      for (final Asset asset : assets) {

         assetMaps.put(asset.getCollectionParameters().getHostAddress(), asset);
      }

      ASSETS = assetMaps;

      EVENTS = events;

      final Thread[] t = new Thread[WORKERS.intValue()];

      for (int i = 0; i < t.length; i++) {

         final String name = "HierarquicalQPCEventWorker-".concat(String.valueOf(i));

         t[i] = new Thread(new HierarquicalQPCEventWorker(this.assetBusiness, super.getAuth(), this.factory, this.eventServiceAdapter), name);

         t[i].start();
      }
   }
}

class HierarquicalQPCEventWorker extends AbstractEventWorker<QPCEventEntity> implements Runnable {

   public HierarquicalQPCEventWorker(AssetBusiness assetBusiness, Auth auth, EntityManagerFactory factory, EventServiceAdapter eventServiceAdapter) {

      super(assetBusiness, auth, factory, eventServiceAdapter);
   }

   @Override
   public void run() {

      Boolean shouldRun = Boolean.TRUE;

      while (shouldRun) {

         try {

            if (!HierarquicalQualysEventProcessor.EVENTS.isEmpty()) {

               final QPCEventEntity entity = this.consume();

               this.process(entity);

               Thread.sleep(AbstractEventWorker.SLEEP);

            } else {

               shouldRun = Boolean.FALSE;
            }
         } catch (final InterruptedException | LackOfAuthorizationException e) {

            shouldRun = Boolean.FALSE;
         }
      }

   }

   @Override
   protected final void process(final QPCEventEntity entity) throws LackOfAuthorizationException, InterruptedException {

      final Event event = new QPCEventEntityToEventConverter().withParent(this.parent(entity.getIpQPC())).convert(entity);

      if (entity.getCode() == null) {

         final Asset asset = HierarquicalQualysEventProcessor.ASSETS.get(entity.getIpQPC());

         if ((asset != null) && (asset.getId() != null)) {

            // super.process(new QualysHostToAssetConverter().convert(new Host(entity.getIpQPC())));

            final String id = super.create(event);

            event.setId(id);

            super.update(event);

            super.associate(event, asset);
         }

      } else {

         if (entity.getStatus() == null) {

            event.setId(entity.getCode());

            event.setComment(entity.getComment());

         } else {

            event.setId(entity.getCode());

            event.setStatus(entity.getStatus().toString());

            event.setComment(entity.getComment());
         }

         super.update(event);
      }

      super.deleteEvent(entity);

   }

   protected final Event parent(final String ipQPC) throws InterruptedException, LackOfAuthorizationException {

      synchronized (HierarquicalQualysEventProcessor.PARENT_EVENT) {

         if (!HierarquicalQualysEventProcessor.PARENT_EVENT.containsKey(ipQPC)) {

            final Event parent = new Event();

            parent.setTitle("Vulnerabilities for host: ".concat(ipQPC));

            parent.setDescription("All vulnerabilities for host ".concat(ipQPC).concat(" can be found on \"Associated Events\" tab"));

            parent.setUrgency(Integer.valueOf(3));

            parent.setEventType(PropertiesUtil.getInstance().getString("QPC.Parent.EventType"));

            final String id = super.create(parent);

            parent.setId(id);

            parent.getCustomAttributes().put("ip_qpc", ipQPC);

            parent.getCustomAttributes().put("qpc_deadline", new LocalDate().plusDays(30).toString());

            super.update(parent);

            HierarquicalQualysEventProcessor.PARENT_EVENT.notifyAll();

            HierarquicalQualysEventProcessor.PARENT_EVENT.put(ipQPC, parent);
         }

         return HierarquicalQualysEventProcessor.PARENT_EVENT.get(ipQPC);
      }
   }

   @Override
   protected final QPCEventEntity consume() throws InterruptedException, LackOfAuthorizationException {

      synchronized (HierarquicalQualysEventProcessor.EVENTS) {

         HierarquicalQualysEventProcessor.EVENTS.notifyAll();

         return HierarquicalQualysEventProcessor.EVENTS.remove(0);
      }
   }

   @Override
   protected final QPCEventEntity getEntity(final EntityManager entityManager, final QPCEventEntity entity) {

      return entityManager.find(QPCEventEntity.class, entity.getEventId());
   }

}