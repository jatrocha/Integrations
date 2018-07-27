package com.modulo.integrations.commons.riskmanager.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.modulo.riskmanager.client.adapter.EventServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.EventFilter;

@Service(value = "eventCountService")
public class EventCountService {

   @Resource
   private EventServiceAdapter eventServiceAdapter;

   private Auth auth;

   private EventFilter filter;

   protected EventCountService(final EventServiceAdapter eventServiceAdapterMock) {

      this();

      this.eventServiceAdapter = eventServiceAdapterMock;
   }

   public EventCountService() {

      super();
   }

   public Integer count() throws LackOfAuthorizationException {

      return this.eventServiceAdapter.count(this.auth, this.filter);
   }

   public EventCountService with(final Auth auth) {

      this.auth = auth;

      return this;
   }

   public EventCountService with(final EventFilter filter) {

      this.filter = filter;

      return this;
   }

}
