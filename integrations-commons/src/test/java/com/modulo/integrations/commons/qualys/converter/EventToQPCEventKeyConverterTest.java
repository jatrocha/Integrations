package com.modulo.integrations.commons.qualys.converter;

import org.junit.Test;

import stubs.EventStub;

import com.modulo.integrations.commons.qualys.to.QPCEventKey;
import com.modulo.riskmanager.client.to.event.Event;

import static junit.framework.Assert.assertEquals;

public class EventToQPCEventKeyConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoEventNull() {

      new EventToQPCEventKeyConverter().convert(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoCustomAttributesEmpty() {

      new EventToQPCEventKeyConverter().convert(new Event());
   }

   @Test
   public void deveConverterQuandoEventValido() {

      final String expected = "8227#AIX 6.1 (Integrity)#10.4.20.17";

      final QPCEventKey actual = new EventToQPCEventKeyConverter().convert(EventStub.get());

      assertEquals(expected, actual.toString());
   }

}
