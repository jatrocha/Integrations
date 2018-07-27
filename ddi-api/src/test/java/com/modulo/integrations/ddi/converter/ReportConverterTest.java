package com.modulo.integrations.ddi.converter;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.jdom2.JDOMException;
import org.junit.Ignore;
import org.junit.Test;

import stubs.DDIReportStub;

import com.modulo.integrations.ddi.to.Host;
import com.modulo.integrations.ddi.to.Vulnerability;
import com.modulo.integrations.ddi.to.VulnerabilityReport;

import static junit.framework.Assert.assertNotNull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ReportConverterTest {

   private static final String REPORT = DDIReportStub.getReport();

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
   public void deveCarregarXML() throws JDOMException, IOException {

      assertNotNull(new ReportConverter().loadReport(REPORT));
   }

   @Test
   @Ignore
   public void deveConverterQuandoArquivoValido() {

      final VulnerabilityReport expected = new ReportConverter().convert(REPORT);

      assertFalse(expected.getHosts().isEmpty());

      final Host host = expected.getHosts().get("crminterview.crm.aflac.com");

      assertEquals("crminterview.crm.aflac.com", host.getName());

      assertEquals("204.76.30.119", host.getIpAddress());

      assertEquals("Windows Server 2003", host.getOperatingSystem());

      assertEquals(StringUtils.EMPTY, host.getParentIp());

      assertFalse(host.getVulnerabilities().isEmpty());

      final Vulnerability vulnActual = host.getVulnerabilities().iterator().next();

      final Vulnerability vulnExpected = this.getVulnerability();

      assertEquals(vulnExpected.getTitle(), vulnActual.getTitle());

      assertEquals(vulnExpected.getRiskLevel(), vulnActual.getRiskLevel());

      assertEquals(vulnExpected.getStatus(), vulnActual.getStatus());

      assertEquals(vulnExpected.getFirstDiscoveryDate(), vulnActual.getFirstDiscoveryDate());

      assertEquals(vulnExpected.getManuallyAdded(), vulnActual.getManuallyAdded());

      assertEquals(vulnExpected.getCvssScore(), vulnActual.getCvssScore());

      assertEquals(vulnExpected.getVulnerabilityId(), vulnActual.getVulnerabilityId());

      assertEquals(vulnExpected.getVulnerabilityTypeId(), vulnActual.getVulnerabilityTypeId());

      assertEquals(vulnExpected.getData(), vulnActual.getData());

      assertEquals(vulnExpected.getVulnDataWasTruncated(), vulnActual.getVulnDataWasTruncated());

      assertEquals(vulnExpected.getSolution(), vulnActual.getSolution());

      assertEquals(vulnExpected.getNetworkProtocol(), vulnActual.getNetworkProtocol());

      assertEquals(vulnExpected.getApplicationProtocol(), vulnActual.getApplicationProtocol());

      assertEquals(vulnExpected.getPort(), vulnActual.getPort());

   }

   private Vulnerability getVulnerability() {

      final Vulnerability vulnerability = new Vulnerability();

      vulnerability.setTitle("Microsoft Windows Server 2003 End of Life");

      vulnerability.setRiskLevel("high");

      vulnerability.setStatus("recurred");

      vulnerability.setFirstDiscoveryDate("2015-11-16 05:34:47");

      vulnerability.setManuallyAdded("0");

      vulnerability.setCvssScore("7.6");

      vulnerability.setId("17691987");

      vulnerability.setVunerabilityTypeId("DDI");

      vulnerability.setData("Support has ended for Windows Server 2003. This host should be immediately upgraded.");

      vulnerability.setDataWasTruncated("no");

      vulnerability.setSolution("If this host is required for production, please upgrade the operating system and ensure it is fully patched.");

      vulnerability.setNetworkProtocol("tcp");

      vulnerability.setApplicationProtocol("unknown");

      vulnerability.setPort(StringUtils.EMPTY);

      return vulnerability;

   }
}
