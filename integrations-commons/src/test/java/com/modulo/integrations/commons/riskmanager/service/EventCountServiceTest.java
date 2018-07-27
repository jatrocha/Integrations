package com.modulo.integrations.commons.riskmanager.service;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.modulo.riskmanager.client.adapter.EventServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.EventFilter;

import static junit.framework.Assert.assertEquals;

public class EventCountServiceTest {

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
   public void deveContarEventosComFiltro() throws LackOfAuthorizationException {

      final EventServiceAdapter eventServiceAdapterMock = this.context.mock(EventServiceAdapter.class);

      final Integer quantidade = Integer.valueOf(10);

      this.context.checking(new Expectations() {

         {

            this.one(eventServiceAdapterMock).count(this.with(any(Auth.class)), this.with(any(EventFilter.class)));
            this.will(returnValue(quantidade));

         }

      });

      assertEquals(quantidade, new EventCountService(eventServiceAdapterMock).with(new Auth()).with(new EventFilter()).count());

      this.context.assertIsSatisfied();
   }

}
