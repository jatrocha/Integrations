package com.modulo.integrations.web.business;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.modulo.riskmanager.client.adapter.EventServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.client.to.event.Event;

@Service(value = "eventBusiness")
public class EventBusiness {

   @Resource
   private EventServiceAdapter eventServiceAdapter;

   private Auth auth;

   private Event event;

   private Asset asset;

   public EventBusiness with(final Auth auth) {

      this.auth = auth;

      return this;
   }

   public EventBusiness with(final Event event) {

      this.event = event;

      return this;
   }

   public void process() throws LackOfAuthorizationException {

      this.event.setId(this.eventServiceAdapter.create(this.auth, this.event));

      this.eventServiceAdapter.update(this.auth, this.event);

      if (this.asset != null) {

         this.eventServiceAdapter.associate(this.auth, this.event, this.asset);

      }
   }

   public EventBusiness with(final Asset asset) {

      this.asset = asset;

      return this;
   }

   protected final void setEventServiceAdapter(final EventServiceAdapter eventServiceAdapter) {

      this.eventServiceAdapter = eventServiceAdapter;
   }

}
