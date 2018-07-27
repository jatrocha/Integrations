package com.modulo.integrations.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.persistence.model.AbstractEventEntity;
import com.modulo.integrations.commons.riskmanager.business.EventListBusiness;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.EventFilter;
import com.modulo.riskmanager.client.to.event.Event;

/**
 * @param <K> EventKey.
 * @param <R> Report.
 * @param <E> Entity.
 */
public abstract class ComplianceProcessor<K, R, E extends AbstractEventEntity> {

   private static final Integer PAGE_SIZE = Integer.valueOf(PropertiesUtil.getInstance().getString("EVENT.PAGE.SIZE"));

   protected final Map<K, Event> getRiskManagerEvents() throws LackOfAuthorizationException {

      final EventFilter filter = new EventFilter(this.getEventType()).withStatus("1").withPageSize(ComplianceProcessor.PAGE_SIZE);

      final Map<String, Event> rmEvents = this.getEventListBusiness().with(this.getAuth()).with(filter).list();

      return this.indexRMEvents(rmEvents);

   }

   protected List<E> processEntities(final Map<K, Event> controlEvents, Map<K, Event> riskManagerEvents) {

      final List<E> entities = new ArrayList<E>();

      for (final K key : controlEvents.keySet()) {

         final Event rm = riskManagerEvents.get(key);

         if ((rm == null) && !this.hasControlPassed(controlEvents, key)) {

            final E entity = this.getConverter().convert(controlEvents.get(key));

            if (this.statusQpcNotEqualsError(entity)) {

               entities.add(this.save(entity));

            }
         }

         if (rm != null) {

            if (!this.hasControlPassed(controlEvents, key)) {

               final E entity = this.getConverter().convert(controlEvents.get(key));

               if (this.statusQpcNotEqualsError(entity)) {

                  entity.setCode(rm.getCode());

                  entity.setComment(this.getFailedComment());

                  entities.add(this.save(entity));
               }

            } else {

               final E entity = this.getConverter().convert(controlEvents.get(key));

               if (this.statusQpcNotEqualsError(entity)) {

                  entity.setCode(rm.getCode());

                  entity.setStatus(Integer.valueOf(2));

                  entity.setComment(this.getPassedComment());

                  entities.add(this.save(entity));
               }
            }

         }
      }

      return entities;
   }

   private boolean statusQpcNotEqualsError(final E entity) {

      return !"Error".equalsIgnoreCase(entity.getStatusQPC());
   }

   private E save(E entity) {

      final EntityManager entityManager = this.getFactory().createEntityManager();

      entityManager.getTransaction().begin();

      final E eventEntity = entityManager.merge(entity);

      entityManager.getTransaction().commit();

      return eventEntity;
   }

   protected abstract Converter<Event, E> getConverter();

   protected abstract EntityManagerFactory getFactory();

   @Deprecated
   // TODO remover
   protected abstract Map<K, Event> controlToEvents(final List<R> list);

   protected abstract Map<K, Event> indexRMEvents(Map<String, Event> events);

   protected abstract EventListBusiness getEventListBusiness();

   protected abstract Auth getAuth();

   protected abstract String getEventType();

   protected abstract Boolean hasControlPassed(final Map<K, Event> controlEvents, final K key);

   protected abstract String getPassedComment();

   protected abstract String getFailedComment();

}
