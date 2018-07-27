package com.modulo.integrations.commons.qualys.converter;

import org.junit.Test;

import stubs.CompilanceReportStub;

import com.modulo.integrations.qualys.to.CompilanceReport;
import com.modulo.integrations.qualys.to.Control;
import com.modulo.integrations.qualys.to.Host;
import com.modulo.riskmanager.client.to.event.Event;

import static junit.framework.Assert.assertEquals;

public class QualysControlToEventConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoControlInvalidoNull() {

      new QualysControlToEventConverter().convert(null);
   }

   @Test
   public void deveConverterQuandoControlValido() {

      final CompilanceReport report = CompilanceReportStub.get();

      final Host host = CompilanceReportStub.getHost();

      final Control source = CompilanceReportStub.getControl();

      final Event target = new QualysControlToEventConverter().with(report).with(host).convert(source);

      assertEquals(source.getStatement(), target.getTitle());

      assertEquals(source.getCheck().getDescription(), target.getDescription());

      assertEquals(Integer.valueOf(5), target.getUrgency());

      assertEquals(Integer.valueOf(source.getCriticalityValue()), target.getSeverity());

      assertEquals(Integer.valueOf(3), target.getRelevance());

      assertEquals(Integer.valueOf(report.getId()), target.getValue()); // TODO: XXXGH

      assertEquals(target.getCustomAttributes().get("cid"), source.getCid());

      assertEquals(target.getCustomAttributes().get("os"), host.getOperatingSystem());

      assertEquals(target.getCustomAttributes().get("ip_qpc"), host.getIp());

      assertEquals(target.getCustomAttributes().get("policy_name"), report.getPolicy());

      assertEquals(target.getCustomAttributes().get("last_scan_date"), host.getLastScanDate());

      assertEquals(target.getCustomAttributes().get("status_qpc"), source.getStatus());

      assertEquals(target.getCustomAttributes().get("expected_evidence"), source.getCheck().getEvaluation());

      assertEquals(target.getCustomAttributes().get("actual_evidence"), source.getCheck().getActualValue());

      assertEquals(target.getCustomAttributes().get("extended_evidence"), source.getCheck().getExtendedEvidence());

      assertEquals(target.getCustomAttributes().get("control_references"), source.getControlReferences());

      assertEquals(target.getCustomAttributes().get("qpc_criticality_label"), source.getCriticalityLabel());

      assertEquals(target.getCustomAttributes().get("qpc_criticality_value"), source.getCriticalityValue());

   }

   @Test
   public void deveConverterQuandoSeverityIgualAZero() {

      final CompilanceReport report = CompilanceReportStub.get();

      final Host host = CompilanceReportStub.getHost();

      final Control source = CompilanceReportStub.getControl();

      source.setCriticalityValue("0");

      final Event target = new QualysControlToEventConverter().with(report).with(host).convert(source);

      assertEquals(source.getStatement(), target.getTitle());

      assertEquals(source.getCheck().getDescription(), target.getDescription());

      assertEquals(Integer.valueOf(5), target.getUrgency());

      assertEquals(Integer.valueOf(1), target.getSeverity());

      assertEquals(Integer.valueOf(3), target.getRelevance());

   }

   @Test
   public void deveConverterQuandoSeverityMaiorQueCinco() {

      final CompilanceReport report = CompilanceReportStub.get();

      final Host host = CompilanceReportStub.getHost();

      final Control source = CompilanceReportStub.getControl();

      source.setCriticalityValue("6");

      final Event target = new QualysControlToEventConverter().with(report).with(host).convert(source);

      assertEquals(source.getStatement(), target.getTitle());

      assertEquals(source.getCheck().getDescription(), target.getDescription());

      assertEquals(Integer.valueOf(5), target.getUrgency());

      assertEquals(Integer.valueOf(5), target.getSeverity());

      assertEquals(Integer.valueOf(3), target.getRelevance());

   }

}
