package com.modulo.integrations.web.business;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.persistence.model.QPCEventEntity;
import com.modulo.integrations.commons.qualys.converter.QPCEventEntityToEventConverter;
import com.modulo.integrations.qualys.to.Host;
import com.modulo.integrations.web.converter.QualysHostToAssetConverter;
import com.modulo.riskmanager.client.adapter.EventServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.client.to.event.Event;

public class FlatQualysEventProcessor extends MultiThreadedEventProcessor<QPCEventEntity> {

   private static final Integer WORKERS = Integer.valueOf(PropertiesUtil.getInstance().getString("NUMBER.OF.WORKERS"));

   @Resource
   private AssetBusiness assetBusiness;

   @Resource
   private EventServiceAdapter eventServiceAdapter;

   @Resource
   private EventBusiness eventBusiness;

   @Resource
   private EntityManagerFactory factory;

   protected static List<QPCEventEntity> EVENTS;

   @Override
   public void process(List<QPCEventEntity> events) throws LackOfAuthorizationException {

      EVENTS = events;

      final Thread[] t = new Thread[WORKERS.intValue()];

      for (int i = 0; i < t.length; i++) {

         final String name = "FlatQPCEventWorker-".concat(String.valueOf(i));

         t[i] = new Thread(new FlatQPCEventWorker(this.assetBusiness, super.getAuth(), this.factory, this.eventServiceAdapter), name);

         t[i].start();
      }
   }
}

class FlatQPCEventWorker extends AbstractEventWorker<QPCEventEntity> implements Runnable {

   public FlatQPCEventWorker(final AssetBusiness assetBusiness, final Auth auth, final EntityManagerFactory factory, final EventServiceAdapter eventServiceAdapter) {

      super(assetBusiness, auth, factory, eventServiceAdapter);
   }

   @Override
   public void run() {

      Boolean shouldRun = Boolean.TRUE;

      while (shouldRun) {

         try {

            if (!FlatQualysEventProcessor.EVENTS.isEmpty()) {

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
   protected final void process(final QPCEventEntity entity) throws LackOfAuthorizationException {

      final Event event = new QPCEventEntityToEventConverter().convert(entity);

      if (entity.getCode() == null) {

         final Asset asset = super.process(new QualysHostToAssetConverter().convert(new Host(entity.getIpQPC())));

         final String id = super.create(event);

         event.setId(id);

         super.update(event);

         super.associate(event, asset);

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

   @Override
   protected final QPCEventEntity consume() throws InterruptedException, LackOfAuthorizationException {

      synchronized (FlatQualysEventProcessor.EVENTS) {

         FlatQualysEventProcessor.EVENTS.notifyAll();

         return FlatQualysEventProcessor.EVENTS.remove(0);
      }
   }

   @Override
   protected final QPCEventEntity getEntity(final EntityManager entityManager, final QPCEventEntity entity) {

      return entityManager.find(QPCEventEntity.class, entity.getEventId());
   }

}