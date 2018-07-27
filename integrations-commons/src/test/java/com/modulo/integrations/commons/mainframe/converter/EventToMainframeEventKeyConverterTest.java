package com.modulo.integrations.commons.mainframe.converter;

import org.junit.Test;

import stubs.MainframeReportStub;

import com.modulo.integrations.commons.mainframe.to.MainframeEventKey;
import com.modulo.riskmanager.client.to.event.Event;

import static org.junit.Assert.assertEquals;

public class EventToMainframeEventKeyConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoEventInvalidoNull() {

      new EventToMainframeEventKeyConverter().convert(null);
   }

   @Test
   public void deveConverterQuandoEventValido() {

      final Event expected = MainframeReportStub.getEvent();

      final MainframeEventKey actual = new EventToMainframeEventKeyConverter().convert(expected);

      assertEquals(expected.getCustomAttributes().get("policy_name"), actual.getPolicyName());

      assertEquals(expected.getCustomAttributes().get("os"), actual.getOperatingSystem());

      assertEquals(expected.getCustomAttributes().get("ref_no"), actual.getRuleRef());

      assertEquals("zOS TA v2.5#zOS#1.1.1", actual.toString());

   }

}
