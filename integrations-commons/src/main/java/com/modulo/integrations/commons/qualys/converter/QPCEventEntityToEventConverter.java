package com.modulo.integrations.commons.qualys.converter;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;

import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.persistence.model.QPCEventEntity;
import com.modulo.riskmanager.client.to.event.Event;

public class QPCEventEntityToEventConverter implements Converter<QPCEventEntity, Event> {

   private Event parent;

   @Override
   public final Event convert(final QPCEventEntity source) {

      final Event event = new Event();

      event.setTitle(source.getTitle());

      event.setDescription(source.getDescription());

      event.setUrgency(source.getUrgency());

      event.setSeverity(source.getSeverity());

      event.setRelevance(source.getRelevance());

      event.setValue(source.getValue());

      event.setEventType(PropertiesUtil.getInstance().getString("QPC.EventType"));

      event.getCustomAttributes().put("cid", source.getCid());

      event.getCustomAttributes().put("os", source.getOs());

      event.getCustomAttributes().put("ip_qpc", source.getIpQPC());

      event.getCustomAttributes().put("policy_name", source.getPolicyName());

      event.getCustomAttributes().put("last_scan_date", source.getLastScanDate());

      event.getCustomAttributes().put("status_qpc", source.getStatusQPC());

      event.getCustomAttributes().put("expected_evidence", source.getExpectedEvidence());

      event.getCustomAttributes().put("actual_evidence", source.getActualEvidence());

      event.getCustomAttributes().put("extended_evidence", source.getExtendedEvidence());

      event.getCustomAttributes().put("control_references", source.getControlReferences());

      event.getCustomAttributes().put("qpc_criticality_label", source.getCriticalityLabel());

      event.getCustomAttributes().put("qpc_criticality_value", source.getCriticalityValue());

      event.getCustomAttributes().put("qpc_deadline", new LocalDate().plusDays(30).toString());

      if (this.parent != null) {

         event.setParentEvent(this.parent.getId());
      }

      return event;

   }

   public QPCEventEntityToEventConverter withParent(final Event parent) {

      if ((parent == null) || StringUtils.isEmpty(parent.getId())) {

         throw new IllegalArgumentException();
      }

      this.parent = parent;

      return this;
   }

}
