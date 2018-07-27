package com.modulo.integrations.commons.mainframe.converter;

import org.junit.Test;

import stubs.MainframeReportStub;

import com.modulo.integrations.commons.persistence.model.MainframeEventEntity;
import com.modulo.riskmanager.client.to.event.Event;

import static org.junit.Assert.assertEquals;

public class EventToMainframeEventEntityConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoEventInvalidoNull() {

      new EventToMainframeEventEntityConverter().convert(null);
   }

   @Test
   public void deveConverterQuandoEventValido() {

      final Event expected = MainframeReportStub.getEvent();

      final MainframeEventEntity actual = new EventToMainframeEventEntityConverter().convert(expected);

      assertEquals(expected.getCustomAttributes().get("policy_name"), actual.getPolicyName());

      assertEquals(expected.getCustomAttributes().get("last_scan_date"), actual.getLastScanDate());

      assertEquals(expected.getCustomAttributes().get("os"), actual.getOs());

      assertEquals(expected.getCustomAttributes().get("ref_no"), actual.getControlReferences());

      assertEquals(expected.getTitle(), actual.getTitle());

      assertEquals(expected.getCustomAttributes().get("status_qpc"), actual.getStatusQPC());

      assertEquals(expected.getDescription(), actual.getDescription());

      assertEquals(expected.getUrgency(), actual.getUrgency());

      assertEquals(expected.getSeverity(), actual.getSeverity());

      assertEquals(expected.getRelevance(), actual.getRelevance());
      
      assertEquals(expected.getAssetsNames(), actual.getAssetNames());
   }

}
