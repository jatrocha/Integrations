package com.modulo.integrations.commons.qualys.converter;

import org.joda.time.LocalDate;
import org.junit.Test;

import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.persistence.model.QPCEventEntity;
import com.modulo.riskmanager.client.to.event.Event;

import static org.junit.Assert.assertEquals;

public class QPCEventEntityToEventConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoParentEventInvalidoNull() {

      final Event parent = null;

      new QPCEventEntityToEventConverter().withParent(parent).convert(this.getEventEntity());
   }

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoParentEventInvalidoVazio() {

      final Event parent = new Event();

      new QPCEventEntityToEventConverter().withParent(parent).convert(this.getEventEntity());
   }

   @Test
   public void deveConverterQuandoParentEventValido() {

      final LocalDate nowPlus30 = new LocalDate().plusDays(30);

      final Event parent = new Event();

      parent.setId("id");

      final QPCEventEntity expected = this.getEventEntity();

      final Event actual = new QPCEventEntityToEventConverter().withParent(parent).convert(expected);

      assertEquals(expected.getTitle(), actual.getTitle());

      assertEquals(expected.getDescription(), actual.getDescription());

      assertEquals(expected.getUrgency(), actual.getUrgency());

      assertEquals(expected.getSeverity(), actual.getSeverity());

      assertEquals(expected.getRelevance(), actual.getRelevance());

      assertEquals(expected.getValue(), actual.getValue());

      assertEquals(PropertiesUtil.getInstance().getString("QPC.EventType"), actual.getEventType());

      assertEquals(expected.getCid(), actual.getCustomAttributes().get("cid"));

      assertEquals(expected.getOs(), actual.getCustomAttributes().get("os"));

      assertEquals(expected.getIpQPC(), actual.getCustomAttributes().get("ip_qpc"));

      assertEquals(expected.getPolicyName(), actual.getCustomAttributes().get("policy_name"));

      assertEquals(expected.getLastScanDate(), actual.getCustomAttributes().get("last_scan_date"));

      assertEquals(expected.getStatusQPC(), actual.getCustomAttributes().get("status_qpc"));

      assertEquals(expected.getExpectedEvidence(), actual.getCustomAttributes().get("expected_evidence"));

      assertEquals(expected.getActualEvidence(), actual.getCustomAttributes().get("actual_evidence"));

      assertEquals(expected.getExtendedEvidence(), actual.getCustomAttributes().get("extended_evidence"));

      assertEquals(expected.getControlReferences(), actual.getCustomAttributes().get("control_references"));

      assertEquals(expected.getCriticalityLabel(), actual.getCustomAttributes().get("qpc_criticality_label"));

      assertEquals(expected.getCriticalityValue(), actual.getCustomAttributes().get("qpc_criticality_value"));

      assertEquals(nowPlus30.toString(), actual.getCustomAttributes().get("qpc_deadline"));

   }

   private QPCEventEntity getEventEntity() {

      final QPCEventEntity event = new QPCEventEntity();

      event.setTitle("Title");

      event.setDescription("Description");

      event.setUrgency(Integer.valueOf(1));

      event.setSeverity(Integer.valueOf(1));

      event.setRelevance(Integer.valueOf(1));

      event.setValue(Integer.valueOf(1));

      // event.setEventType(PropertiesUtil.getInstance().getString("QPC.EventType"));

      event.setCid("cid");

      event.setOs("OS");

      event.setIpQPC("ipQPC");

      event.setPolicyName("PolicyName");

      event.setLastScanDate("LastScanDate");

      event.setStatusQPC("StatusQPC");

      event.setExpectedEvidence("Expected Evidence");

      event.setActualEvidence("Actual Evidence");

      event.setExtendedEvidence("Extended Evidence");

      event.setControlReferences("Control References");

      event.setCriticalityLabel("Criticaly Label");

      event.setCriticalityValue("Criticaly Label");

      return event;
   }

}
