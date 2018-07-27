package com.modulo.integrations.commons.qualys.converter;

import org.apache.commons.lang.StringUtils;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.qualys.to.AbstractCompilanceReport;
import com.modulo.integrations.qualys.to.Control;
import com.modulo.integrations.qualys.to.Host;
import com.modulo.riskmanager.client.to.event.Event;

public class QualysControlToEventConverter implements Converter<Control, Event> {

   private AbstractCompilanceReport report;

   private Host host;

   @Override
   public Event convert(Control source) {

      if ((this.report == null) || (this.host == null) || (null == source)) {

         throw new IllegalArgumentException();
      }

      final Event target = new Event();

      target.setTitle(source.getStatement());

      target.setDescription(source.getCheck().getDescription());

      target.setUrgency(Integer.valueOf(5));

      target.setValue(Integer.valueOf(this.report.getId()));

      Integer severity = Integer.valueOf(source.getCriticalityValue());

      if (Integer.valueOf(0).equals(severity)) {

         severity = 1;
      }

      if (Integer.valueOf(5).compareTo(severity) == -1) {

         severity = 5;
      }

      target.setSeverity(severity);

      target.setRelevance(Integer.valueOf(3));

      target.getCustomAttributes().put("cid", source.getCid());

      target.getCustomAttributes().put("os", this.host.getOperatingSystem());

      target.getCustomAttributes().put("ip_qpc", this.host.getIp());

      target.getCustomAttributes().put("policy_name", this.report.getPolicy());

      target.getCustomAttributes().put("last_scan_date", this.host.getLastScanDate());

      target.getCustomAttributes().put("status_qpc", source.getStatus());

      String expectedEvidence = source.getCheck().getEvaluation();

      if (StringUtils.isNotEmpty(source.getCheck().getEvaluationValue())) {

         expectedEvidence = expectedEvidence.concat(" \n\n ").concat(source.getCheck().getEvaluationValue().trim());
      }

      // target.getCustomAttributes().put("expected_evidence", source.getCheck().getEvaluationValue());

      target.getCustomAttributes().put("expected_evidence", expectedEvidence);

      target.getCustomAttributes().put("actual_evidence", source.getCheck().getActualValue());

      target.getCustomAttributes().put("extended_evidence", source.getCheck().getExtendedEvidence());

      target.getCustomAttributes().put("control_references", source.getControlReferences());

      target.getCustomAttributes().put("qpc_criticality_label", source.getCriticalityLabel());

      target.getCustomAttributes().put("qpc_criticality_value", source.getCriticalityValue());

      return target;
   }

   public QualysControlToEventConverter with(final AbstractCompilanceReport report) {

      this.report = report;

      return this;
   }

   public QualysControlToEventConverter with(final Host host) {

      this.host = host;

      return this;
   }

}
