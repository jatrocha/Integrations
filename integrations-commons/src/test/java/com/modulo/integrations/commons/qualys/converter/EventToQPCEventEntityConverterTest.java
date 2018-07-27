package com.modulo.integrations.commons.qualys.converter;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.modulo.integrations.commons.persistence.model.QPCEventEntity;
import com.modulo.riskmanager.client.to.event.Event;

import static junit.framework.Assert.assertEquals;

public class EventToQPCEventEntityConverterTest {

   @Test
   public void deveConverterQuandoTodosCamposPreenchidos() {

      final Event expected = this.getEvent();

      final QPCEventEntity actual = new EventToQPCEventEntityConverter().convert(expected);

      assertEquals(expected.getTitle(), actual.getTitle());

      assertEquals(expected.getDescription(), actual.getDescription());

      assertEquals(expected.getUrgency(), actual.getUrgency());

      assertEquals(expected.getSeverity(), actual.getSeverity());

      assertEquals(expected.getRelevance(), actual.getRelevance());

      assertEquals(expected.getValue(), actual.getValue());

      assertEquals(expected.getCustomAttributes().get("cid"), actual.getCid());

      assertEquals(expected.getCustomAttributes().get("os"), actual.getOs());

      assertEquals(expected.getCustomAttributes().get("ip_qpc"), actual.getIpQPC());

      assertEquals(expected.getCustomAttributes().get("policy_name"), actual.getPolicyName());

      assertEquals(expected.getCustomAttributes().get("last_scan_date"), actual.getLastScanDate());

      assertEquals(expected.getCustomAttributes().get("status_qpc"), actual.getStatusQPC());

      assertEquals(expected.getCustomAttributes().get("expected_evidence"), actual.getExpectedEvidence());

      assertEquals(expected.getCustomAttributes().get("actual_evidence"), actual.getActualEvidence());

      assertEquals(expected.getCustomAttributes().get("extended_evidence"), actual.getExtendedEvidence());

      assertEquals(expected.getCustomAttributes().get("control_references"), actual.getControlReferences());

      assertEquals(expected.getCustomAttributes().get("qpc_criticality_label"), actual.getCriticalityLabel());

      assertEquals(expected.getCustomAttributes().get("qpc_criticality_value"), actual.getCriticalityValue());

   }


   @Test
   public void deveConverterQuandoNemTodosDadosInformados() {

      final Event expected = this.getEventQuandoNemTodosDadosInformados();

      final QPCEventEntity actual = new EventToQPCEventEntityConverter().convert(expected);

      assertEquals(expected.getTitle(), actual.getTitle());

      assertEquals(expected.getDescription(), actual.getDescription());

      assertEquals(expected.getUrgency(), actual.getUrgency());

      assertEquals(expected.getSeverity(), actual.getSeverity());

      assertEquals(expected.getRelevance(), actual.getRelevance());

      assertEquals(expected.getValue(), actual.getValue());

      assertEquals(expected.getCustomAttributes().get("cid"), actual.getCid());

      assertEquals(expected.getCustomAttributes().get("os"), actual.getOs());

      assertEquals(expected.getCustomAttributes().get("ip_qpc"), actual.getIpQPC());

      assertEquals(expected.getCustomAttributes().get("policy_name"), actual.getPolicyName());

      assertEquals(expected.getCustomAttributes().get("last_scan_date"), actual.getLastScanDate());

      assertEquals(expected.getCustomAttributes().get("status_qpc"), actual.getStatusQPC());

      assertNull(expected.getCustomAttributes().get("expected_evidence"));
      
      assertNull(expected.getCustomAttributes().get("actual_evidence"));
      
      assertEquals(expected.getCustomAttributes().get("extended_evidence"), actual.getExtendedEvidence());

      assertNull(expected.getCustomAttributes().get("control_references"));
      
      assertEquals(expected.getCustomAttributes().get("qpc_criticality_label"), actual.getCriticalityLabel());

      assertEquals(expected.getCustomAttributes().get("qpc_criticality_value"), actual.getCriticalityValue());

   }

   
   private Event getEvent() {
	   
	   final Event event = new Event();
	   
	   event.setTitle("Title");
	   
	   event.setDescription("Description");
	   
	   event.setUrgency(Integer.valueOf(1));
	   
	   event.setSeverity(Integer.valueOf(1));
	   
	   event.setRelevance(Integer.valueOf(1));
	   
	   event.setValue(Integer.valueOf(1150007));
	   
	   event.getCustomAttributes().put("cid", "cid");
	   
	   event.getCustomAttributes().put("os", "os");
	   
	   event.getCustomAttributes().put("ip_qpc", "ip_qpc");
	   
	   event.getCustomAttributes().put("policy_name", "policy_name");
	   
	   event.getCustomAttributes().put("last_scan_date", "last_scan_date");
	   
	   event.getCustomAttributes().put("status_qpc", "status_qpc");
	   
	   event.getCustomAttributes().put("expected_evidence", "expected_evidence");
	   
	   event.getCustomAttributes().put("actual_evidence", "actual_evidence");
	   
	   event.getCustomAttributes().put("extended_evidence", "extended_evidence");
	   
	   event.getCustomAttributes().put("control_references", "control_references");
	   
	   event.getCustomAttributes().put("qpc_criticality_label", "qpc_criticality_label");
	   
	   event.getCustomAttributes().put("qpc_criticality_value", "qpc_criticality_value");
	   
	   return event;
   }
   
   private Event getEventQuandoNemTodosDadosInformados() {
	   
	   final Event event = new Event();
	   
	   event.setTitle("Title");
	   
	   event.setDescription("Description");
	   
	   event.setUrgency(Integer.valueOf(1));
	   
	   event.setSeverity(Integer.valueOf(1));
	   
	   event.setRelevance(Integer.valueOf(1));
	   
	   event.setValue(Integer.valueOf(1150007));
	   
	   event.getCustomAttributes().put("cid", "cid");
	   
	   event.getCustomAttributes().put("os", "os");
	   
	   event.getCustomAttributes().put("ip_qpc", "ip_qpc");
	   
	   event.getCustomAttributes().put("policy_name", "policy_name");
	   
	   event.getCustomAttributes().put("last_scan_date", "last_scan_date");
	   
	   event.getCustomAttributes().put("status_qpc", "status_qpc");
	   
	   event.getCustomAttributes().put("qpc_criticality_label", "qpc_criticality_label");
	   
	   event.getCustomAttributes().put("qpc_criticality_value", "qpc_criticality_value");
	   
	   return event;
   }

   
}
