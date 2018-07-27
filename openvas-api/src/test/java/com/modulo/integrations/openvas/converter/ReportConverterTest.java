package com.modulo.integrations.openvas.converter;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import stubs.OpenVASReportStub;

import com.modulo.integrations.openvas.to.Vulnerability;
import com.modulo.integrations.openvas.to.VulnerabilityReport;

public class ReportConverterTest {

   private static final String REPORT = OpenVASReportStub.getReport();

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoArquivoInvalidoNull() {

      new ReportConverter().convert(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoArquivoInvalidoVazio() {

      new ReportConverter().convert(StringUtils.EMPTY);
   }

   @Test
   @Ignore
   public void deveConverterQuandoArquivoValido() {

      final VulnerabilityReport expected = new ReportConverter().convert(REPORT);

   }

   private Vulnerability getVulnerability() {

      final Vulnerability vulnerability = new Vulnerability();

      return vulnerability;

   }
}
