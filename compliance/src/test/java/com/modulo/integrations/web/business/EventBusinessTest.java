package com.modulo.integrations.web.business;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.modulo.riskmanager.client.adapter.EventServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.client.to.event.Event;

public class EventBusinessTest {

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
   public void deveGravarEventQuandoAssetDevaSerAssociado() throws LackOfAuthorizationException {

      final EventServiceAdapter eventServiceAdapterMock = this.context.mock(EventServiceAdapter.class);

      final Event event = new Event();

      final String code = "EVT001";

      event.setCode(code);

      this.context.checking(new Expectations() {

         {

            this.one(eventServiceAdapterMock).create(this.with(any(Auth.class)), this.with(any(Event.class)));
            this.will(returnValue(code));

            this.one(eventServiceAdapterMock).update(this.with(any(Auth.class)), this.with(any(Event.class)));

            this.one(eventServiceAdapterMock).associate(this.with(any(Auth.class)), this.with(any(Event.class)), this.with(any(Asset.class)));

         }

      });

      final EventBusiness business = new EventBusiness();

      business.setEventServiceAdapter(eventServiceAdapterMock);

      business.with(new Auth()).with(new Event()).with(new Asset()).process();

      this.context.assertIsSatisfied();
   }

   @Test
   public void deveGravarEventQuandoAssetNaoDevaSerAssociado() throws LackOfAuthorizationException {

      final EventServiceAdapter eventServiceAdapterMock = this.context.mock(EventServiceAdapter.class);

      final Event event = new Event();

      final String code = "EVT001";

      event.setCode(code);

      this.context.checking(new Expectations() {

         {

            this.one(eventServiceAdapterMock).create(this.with(any(Auth.class)), this.with(any(Event.class)));
            this.will(returnValue(code));

            this.one(eventServiceAdapterMock).update(this.with(any(Auth.class)), this.with(any(Event.class)));

            this.never(eventServiceAdapterMock).associate(this.with(any(Auth.class)), this.with(any(Event.class)), this.with(any(Asset.class)));

         }

      });

      final EventBusiness business = new EventBusiness();

      business.setEventServiceAdapter(eventServiceAdapterMock);

      business.with(new Auth()).with(new Event()).process();

      this.context.assertIsSatisfied();
   }

}
