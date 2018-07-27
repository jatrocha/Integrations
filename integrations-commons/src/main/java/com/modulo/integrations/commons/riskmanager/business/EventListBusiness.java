package com.modulo.integrations.commons.riskmanager.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.modulo.integrations.commons.riskmanager.service.EventCountService;
import com.modulo.integrations.commons.riskmanager.service.EventListService;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.EventFilter;
import com.modulo.riskmanager.client.to.event.Event;

@Service(value = "eventListBusiness")
public class EventListBusiness {

   @Resource
   private EventCountService eventCountService;

   @Resource
   private EventListService eventListService;

   protected Auth auth;

   protected EventFilter filter;

   protected EventListBusiness(final EventCountService eventCountService, final EventListService eventListService) {

      this();

      this.eventCountService = eventCountService;

      this.eventListService = eventListService;

   }

   public EventListBusiness() {

      super();
   }

   public EventListBusiness with(final Auth auth) {

      this.auth = auth;

      return this;
   }

   public EventListBusiness with(final EventFilter filter) {

      this.filter = filter;

      return this;
   }

   public Map<String, Event> list() throws LackOfAuthorizationException {

      if ((this.auth == null) || (this.filter == null)) {

         throw new IllegalArgumentException();
      }

      final Integer qtdeEventos = this.eventCountService.with(this.auth).with(this.filter).count();

      final Integer qtdeRequisicoes = this.getQtdeRequisicoes(qtdeEventos);

      Integer contador = Integer.valueOf(1);

      final Map<String, Event> retorno = new HashMap<String, Event>(qtdeEventos);

      while (contador <= qtdeRequisicoes) {

         final List<Event> list = this.eventListService.with(this.auth).with(this.filter.forPage(contador)).list();

         for (final Event event : list) {

            retorno.put(event.getCode(), event);

         }

         contador++;

      }

      return retorno;

   }

   protected final Integer getQtdeRequisicoes(final Integer qtdeEventos) {

      final Integer pageSize = Integer.valueOf(this.filter.getPageSize());

      Integer qtdeRequisicoes = qtdeEventos / pageSize;

      return ((qtdeEventos.intValue() % pageSize.intValue()) >= 1) ? ++qtdeRequisicoes : qtdeRequisicoes;
   }

}
