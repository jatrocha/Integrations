package com.modulo.integrations.commons.riskmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.modulo.riskmanager.client.adapter.EventServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.EventFilter;
import com.modulo.riskmanager.client.to.event.Event;

import static org.junit.Assert.assertNotNull;

public class EventListServiceTest {

   private Mockery context;

   @Before
   public void init() {

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);

         }

      };
   }

   @Test
   public void deveListarEventosComFiltro() throws LackOfAuthorizationException {

      final EventServiceAdapter eventServiceAdapterMock = this.context.mock(EventServiceAdapter.class);

      final List<Event> expected = new ArrayList<Event>();

      this.context.checking(new Expectations() {

         {

            this.one(eventServiceAdapterMock).list(this.with(any(Auth.class)), this.with(any(EventFilter.class)));
            this.will(returnValue(expected));

         }

      });

      final List<Event> actual = new EventListService(eventServiceAdapterMock).with(new Auth()).with(new EventFilter()).list();

      assertNotNull(actual);

      this.context.assertIsSatisfied();

   }

}
