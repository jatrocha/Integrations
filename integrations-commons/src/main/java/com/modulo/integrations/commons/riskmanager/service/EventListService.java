package com.modulo.integrations.commons.riskmanager.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.modulo.riskmanager.client.adapter.EventServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.EventFilter;
import com.modulo.riskmanager.client.to.event.Event;

@Service(value = "eventListService")
public class EventListService {

   @Resource
   private EventServiceAdapter eventServiceAdapter;

   private Auth auth;

   private EventFilter filter;

   protected EventListService(final EventServiceAdapter eventServiceAdapterMock) {

      this();

      this.eventServiceAdapter = eventServiceAdapterMock;
   }

   public EventListService() {

      super();
   }

   public List<Event> list() throws LackOfAuthorizationException {

      return this.eventServiceAdapter.list(this.auth, this.filter);
   }

   public EventListService with(final Auth auth) {

      this.auth = auth;

      return this;
   }

   public EventListService with(final EventFilter filter) {

      this.filter = filter;

      return this;
   }

}
