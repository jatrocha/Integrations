package stubs;

import com.modulo.riskmanager.client.to.event.Event;

public class EventStub {

   public static Event get() {

      final Event event = new Event();

      event.setId("EVT001");

      event.getCustomAttributes().put("cid", "8227");

      event.getCustomAttributes().put("os", "AIX 6.1");

      event.getCustomAttributes().put("ip_qpc", "10.4.20.17");

      event.getCustomAttributes().put("policy_name", "AIX 6.1 (Integrity)");

      event.getCustomAttributes().put("last_scan_date", "2016-05-29T01:06:23Z");

      event.getCustomAttributes().put("status_qpc", "Failed");

      event.getCustomAttributes().put("expected_evidence", "expected_evidence");

      event.getCustomAttributes().put("actual_evidence", "actual_evidence");

      event.getCustomAttributes().put("extended_evidence", "extended_evidence");

      event.getCustomAttributes().put("control_references", "control_reference");

      return event;

   }

}
