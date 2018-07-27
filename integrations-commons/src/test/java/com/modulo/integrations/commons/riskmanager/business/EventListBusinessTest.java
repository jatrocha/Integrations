package com.modulo.integrations.commons.riskmanager.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import stubs.EventStub;

import com.modulo.integrations.commons.qualys.to.QPCEventKey;
import com.modulo.integrations.commons.riskmanager.service.EventCountService;
import com.modulo.integrations.commons.riskmanager.service.EventListService;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.EventFilter;
import com.modulo.riskmanager.client.to.event.Event;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class EventListBusinessTest {

   private Mockery context;

   @Before
   public void init() {

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);

         }

      };
   }

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveListarQuandoAuthInvalidoNull() throws LackOfAuthorizationException {

      final Auth auth = null;

      final EventFilter filter = null;

      new EventListBusiness().with(auth).with(filter).list();

   }

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveListarQuandoFilterInvalidoNull() throws LackOfAuthorizationException {

      final Auth auth = new Auth();

      final EventFilter filter = null;

      new EventListBusiness().with(auth).with(filter).list();

   }

   @Test
   public void deveListarEventosDeAcordoComOFiltroComQuantidadeMenorQueTamanhoDePagina() throws LackOfAuthorizationException {

      final EventCountService eventCountServiceMock = this.context.mock(EventCountService.class);

      final EventListService eventListServiceMock = this.context.mock(EventListService.class);

      final Integer quantidade = Integer.valueOf(999);

      final List<Event> eventList = new ArrayList<Event>(999);

      eventList.add(EventStub.get());

      final Map<QPCEventKey, Event> expected = new HashMap<QPCEventKey, Event>(quantidade);

      expected.put(new QPCEventKey(), EventStub.get());

      this.context.checking(new Expectations() {

         {

            this.one(eventCountServiceMock).with(this.with(any(Auth.class)));
            this.will(returnValue(eventCountServiceMock));

            this.one(eventCountServiceMock).with(this.with(any(EventFilter.class)));
            this.will(returnValue(eventCountServiceMock));

            this.one(eventCountServiceMock).count();
            this.will(returnValue(quantidade));

            this.one(eventListServiceMock).with(this.with(any(Auth.class)));
            this.will(returnValue(eventListServiceMock));

            this.one(eventListServiceMock).with(this.with(any(EventFilter.class)));
            this.will(returnValue(eventListServiceMock));

            this.one(eventListServiceMock).list();
            this.will(returnValue(eventList));

         }

      });

      final EventFilter filter = new EventFilter("NonCompliance - QPC").withStatus("1").withPageSize(1000).forPage(1);

      final Auth auth = new Auth("123", "asdf", Integer.valueOf(1));

      final Map<String, Event> actual = new EventListBusiness(eventCountServiceMock, eventListServiceMock).with(auth).with(filter).list();

      assertNotNull(actual);

      assertFalse(actual.isEmpty());

      this.context.assertIsSatisfied();

   }

   @Test
   public void deveListarEventosDeAcordoComOFiltroComQuantidadeMaiorQueTamanhoDePagina() throws LackOfAuthorizationException {

      final EventCountService eventCountServiceMock = this.context.mock(EventCountService.class);

      final EventListService eventListServiceMock = this.context.mock(EventListService.class);

      final Integer quantidade = Integer.valueOf(10001);

      final List<Event> eventList = new ArrayList<Event>(10001);

      eventList.add(EventStub.get());

      final Map<QPCEventKey, Event> expected = new HashMap<QPCEventKey, Event>(quantidade);

      expected.put(new QPCEventKey(), EventStub.get());

      this.context.checking(new Expectations() {

         {

            this.one(eventCountServiceMock).with(this.with(any(Auth.class)));
            this.will(returnValue(eventCountServiceMock));

            this.one(eventCountServiceMock).with(this.with(any(EventFilter.class)));
            this.will(returnValue(eventCountServiceMock));

            this.one(eventCountServiceMock).count();
            this.will(returnValue(quantidade));

            this.exactly(11).of(eventListServiceMock).with(this.with(any(Auth.class)));
            this.will(returnValue(eventListServiceMock));

            this.exactly(11).of(eventListServiceMock).with(this.with(any(EventFilter.class)));
            this.will(returnValue(eventListServiceMock));

            this.exactly(11).of(eventListServiceMock).list();
            this.will(returnValue(eventList));

         }

      });

      final EventFilter filter = new EventFilter("NonCompliance - QPC").withStatus("1").withPageSize(1000).forPage(1);

      final Auth auth = new Auth("123", "asdf", Integer.valueOf(1));

      final Map<String, Event> actual = new EventListBusiness(eventCountServiceMock, eventListServiceMock).with(auth).with(filter).list();

      assertNotNull(actual);

      assertFalse(actual.isEmpty());

      this.context.assertIsSatisfied();

   }
}
