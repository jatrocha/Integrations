package com.modulo.integrations.web.business;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.riskmanager.client.adapter.EventServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.client.to.event.Event;

public abstract class AbstractEventWorker<T> implements Runnable {

   protected static final Integer SLEEP = Integer.valueOf(PropertiesUtil.getInstance().getString("TIME.BETWEEN.REQUESTS"));

   private final EntityManagerFactory factory;

   private final AssetBusiness assetBusiness;

   private final Auth auth;

   private final EventServiceAdapter eventServiceAdapter;

   public AbstractEventWorker(final AssetBusiness assetBusiness, final Auth auth, final EntityManagerFactory factory, final EventServiceAdapter eventServiceAdapter) {

      super();

      this.assetBusiness = assetBusiness;

      this.auth = auth;

      this.factory = factory;

      this.eventServiceAdapter = eventServiceAdapter;

   }

   protected void deleteEvent(T entity) {

      final EntityManager entityManager = this.factory.createEntityManager();

      final T find = this.getEntity(entityManager, entity);

      entityManager.getTransaction().begin();

      entityManager.remove(find);

      entityManager.getTransaction().commit();
   }

   protected final EntityManagerFactory getEntityManagerFactory() {

      return this.factory;
   }

   protected final String create(final Event event) throws LackOfAuthorizationException {

      return this.eventServiceAdapter.create(this.auth, event);
   }

   protected final void update(final Event event) throws LackOfAuthorizationException {

      this.eventServiceAdapter.update(this.auth, event);
   }

   protected final void associate(final Event event, final Asset asset) throws LackOfAuthorizationException {

      this.eventServiceAdapter.associate(this.auth, event, asset);
   }

   protected final Asset process(final Asset asset) throws LackOfAuthorizationException {

      return this.assetBusiness.with(this.auth).with(asset).process();
   }

   protected abstract void process(final T entity) throws InterruptedException, LackOfAuthorizationException;

   protected abstract T consume() throws InterruptedException, LackOfAuthorizationException;

   protected abstract T getEntity(final EntityManager entityManager, final T entity);

}
