package com.modulo.integrations.commons.mainframe.converter;

import org.junit.Test;

import stubs.MainframeReportStub;

import com.modulo.integrations.mainframe.to.Control;
import com.modulo.riskmanager.client.to.event.Event;

import static org.junit.Assert.assertEquals;

public class MainframeControlToEventConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoControleInvalidoNull() {

      new MainframeControlToEventConverter().convert(null);
   }

   @Test
   public void deveConverterQuandoControleValido() {

      final Control expected = MainframeReportStub.getControl();

      final Event actual = new MainframeControlToEventConverter().convert(expected);

      assertEquals(expected.getPolicyName(), actual.getCustomAttributes().get("policy_name"));

      assertEquals(expected.getLastScanDate(), actual.getCustomAttributes().get("last_scan_date"));

      assertEquals(expected.getOperatingSystem(), actual.getCustomAttributes().get("os"));

      assertEquals(expected.getRuleRef(), actual.getCustomAttributes().get("ref_no"));

      assertEquals(expected.getStatement(), actual.getTitle());

      assertEquals(expected.getStatus(), actual.getCustomAttributes().get("status_qpc"));

      assertEquals(expected.getDescription(), actual.getDescription());
      
      assertEquals(expected.getAsset(), actual.getAssetsNames());

   }

}
