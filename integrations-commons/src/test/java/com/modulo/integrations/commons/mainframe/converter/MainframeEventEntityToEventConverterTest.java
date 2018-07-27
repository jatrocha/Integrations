package com.modulo.integrations.commons.mainframe.converter;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import stubs.MainframeReportStub;

import com.modulo.integrations.commons.persistence.model.MainframeEventEntity;
import com.modulo.integrations.mainframe.to.Control;
import com.modulo.riskmanager.client.to.event.Event;

import static org.junit.Assert.assertEquals;

public class MainframeEventEntityToEventConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoEntityInvalidaNull() {

      new MainframeEventEntityToEventConverter().convert(null);
   }

   @Test
   public void deveConverterQuandoEntityValida() {

      final MainframeEventEntity expected = this.getMainframeEventEntity();

      final Event actual = new MainframeEventEntityToEventConverter().convert(expected);

      assertEquals(expected.getPolicyName(), actual.getCustomAttributes().get("policy_name"));

      assertEquals(expected.getLastScanDate(), actual.getCustomAttributes().get("last_scan_date"));

      assertEquals(expected.getOs(), actual.getCustomAttributes().get("os"));

      assertEquals(expected.getControlReferences(), actual.getCustomAttributes().get("ref_no"));

      assertEquals(StringUtils.substring(actual.getTitle(), 0, 400), actual.getTitle());

      assertEquals(expected.getStatusQPC(), actual.getCustomAttributes().get("status_qpc"));

      assertEquals(expected.getDescription(), actual.getDescription());

      assertEquals(expected.getSeverity(), actual.getSeverity());

      assertEquals(expected.getRelevance(), actual.getRelevance());

      assertEquals(expected.getUrgency(), actual.getUrgency());
      
      assertEquals(expected.getAssetNames(), actual.getAssetsNames());

   }

   private MainframeEventEntity getMainframeEventEntity() {

      final MainframeEventEntity entity = new MainframeEventEntity();

      final Control control = MainframeReportStub.getControl();

      entity.setPolicyName(control.getPolicyName());

      entity.setLastScanDate(control.getLastScanDate());

      entity.setOs(control.getOperatingSystem());

      entity.setControlReferences(control.getRuleRef());

      entity.setTitle(control.getStatement());

      entity.setDescription(control.getDescription());

      entity.setSeverity(Integer.valueOf(3));

      entity.setRelevance(Integer.valueOf(3));

      entity.setUrgency(Integer.valueOf(3));
      
      entity.setAssetNames("ASSET");

      return entity;
   }

}
