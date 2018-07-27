package com.modulo.integrations.web.business;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Service;

import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.ddi.converter.DDIEventEntityToAssetConverter;
import com.modulo.integrations.commons.ddi.converter.DDIEventEntityToEventConverter;
import com.modulo.integrations.commons.persistence.model.DDIEventEntity;
import com.modulo.riskmanager.client.adapter.EventServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.client.to.event.Event;

@Service(value = "ddiEventProcessor")
public class DDIEventProcessor extends MultiThreadedEventProcessor<DDIEventEntity> {

   private static final Integer WORKERS = Integer.valueOf(PropertiesUtil.getInstance().getString("NUMBER.OF.WORKERS"));

   @Resource
   private AssetBusiness assetBusiness;

   @Resource
   private EventServiceAdapter eventServiceAdapter;

   @Resource
   private EventBusiness eventBusiness;

   @Resource
   private EntityManagerFactory factory;

   protected static List<DDIEventEntity> EVENTS;

   @Override
   public void process(final List<DDIEventEntity> events) throws LackOfAuthorizationException {

      EVENTS = events;

      final Thread[] t = new Thread[WORKERS.intValue()];

      for (int i = 0; i < t.length; i++) {

         final String name = "DDIEventWorker-".concat(String.valueOf(i));

         t[i] = new Thread(new DDIEventWorker(this.assetBusiness, super.getAuth(), this.factory, this.eventServiceAdapter), name);

         t[i].start();
      }
   }

   @Deprecated
   protected void deleteEvent(DDIEventEntity entity) {

      final EntityManager entityManager = this.factory.createEntityManager();

      final DDIEventEntity find = entityManager.find(DDIEventEntity.class, entity.getEventId());

      entityManager.getTransaction().begin();

      entityManager.remove(find);

      entityManager.getTransaction().commit();

   }

   @Deprecated
   protected String getEventType() {

      return PropertiesUtil.getInstance().getString("DDI.EventType");
   }

}

class DDIEventWorker implements Runnable {

   private static final Integer SLEEP = Integer.valueOf(PropertiesUtil.getInstance().getString("TIME.BETWEEN.REQUESTS"));

   private final AssetBusiness assetBusiness;

   private final Auth auth;

   private final EntityManagerFactory factory;

   private final EventServiceAdapter eventServiceAdapter;

   public DDIEventWorker(final AssetBusiness assetBusiness, final Auth auth, final EntityManagerFactory factory, final EventServiceAdapter eventServiceAdapter) {

      super();

      this.assetBusiness = assetBusiness;

      this.auth = auth;

      this.factory = factory;

      this.eventServiceAdapter = eventServiceAdapter;

   }

   @Override
   public void run() {

      Boolean shouldRun = Boolean.TRUE;

      while (shouldRun) {

         try {

            if (!DDIEventProcessor.EVENTS.isEmpty()) {

               final DDIEventEntity entity = this.consume();

               this.process(entity);

               Thread.sleep(SLEEP);

            } else {

               shouldRun = Boolean.FALSE;
            }
         } catch (final InterruptedException | LackOfAuthorizationException e) {

            shouldRun = Boolean.FALSE;
         }
      }
   }

   private void process(DDIEventEntity entity) throws LackOfAuthorizationException {

      final Asset asset = this.assetBusiness.with(this.auth).with(new DDIEventEntityToAssetConverter().convert(entity)).process();

      final Event event = new DDIEventEntityToEventConverter().convert(entity);

      if (entity.getCode() == null) {

         final String id = this.eventServiceAdapter.create(this.auth, event);

         event.setId(id);

         this.eventServiceAdapter.update(this.auth, event);

         this.eventServiceAdapter.associate(this.auth, event, asset);
      }

      if (entity.getCode() != null) {

         if (entity.getStatus() == null) {

            event.setId(entity.getCode());

            event.setComment(entity.getComment());

            this.eventServiceAdapter.update(this.auth, event);

         } else {

            event.setId(entity.getCode());

            event.setStatus(entity.getStatus().toString());

            event.setComment(entity.getComment());

            this.eventServiceAdapter.update(this.auth, event);
         }
      }

      this.deleteEvent(entity);

   }

   private DDIEventEntity consume() throws InterruptedException, LackOfAuthorizationException {

      synchronized (DDIEventProcessor.EVENTS) {

         DDIEventProcessor.EVENTS.notifyAll();

         return DDIEventProcessor.EVENTS.remove(0);
      }
   }

   protected void deleteEvent(DDIEventEntity entity) {

      final EntityManager entityManager = this.factory.createEntityManager();

      final DDIEventEntity find = entityManager.find(DDIEventEntity.class, entity.getEventId());

      entityManager.getTransaction().begin();

      entityManager.remove(find);

      entityManager.getTransaction().commit();
   }

}
