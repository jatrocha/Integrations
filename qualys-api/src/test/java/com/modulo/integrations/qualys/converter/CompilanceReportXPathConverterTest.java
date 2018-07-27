package com.modulo.integrations.qualys.converter;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import stubs.QPCReportsStub;

import com.modulo.integrations.qualys.to.CompilanceReport;
import com.modulo.integrations.qualys.to.Control;
import com.modulo.integrations.qualys.to.Host;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Unit test for simple App.
 */
public class CompilanceReportXPathConverterTest {

   private static final String REPORT_11851007 = QPCReportsStub.getReport11851007();

   private static final String REPORT_11850990 = QPCReportsStub.getReport11850990();

   private static final String REPORT_11850960 = QPCReportsStub.getReport11850960();

   private static final String REPORT_11850958 = QPCReportsStub.getReport11850958();

   private static final String REPORT_11850932 = QPCReportsStub.getReport11850932();

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoArquivoInvalidoNull() {

      new CompilanceReportXPathConverter().convert(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoArquivoInvalidoVazio() {

      new CompilanceReportXPathConverter().convert(StringUtils.EMPTY);
   }

   @Test
   public void deveConverterREPORT_11851007() {

      final CompilanceReport report = new CompilanceReportXPathConverter().convert(REPORT_11851007);

      assertEquals("AIX Modulo (New)", report.getName());

      assertEquals("AIX 6.1 (Integrity)", report.getPolicy());

      final Host host = report.getHosts().get("10.4.20.17");

      // assertEquals(Integer.valueOf(94), Integer.valueOf(report.getHosts().size()));

      assertEquals("2016-05-29T01:06:23Z", host.getLastScanDate());

      assertEquals("AIX 6.1", host.getOperatingSystem());

      assertEquals("10.4.20.17", host.getIp());

      final Control control = host.getControls().get("8227");

      if (control == null) {
         System.out.println();
      }

      assertEquals("8227", control.getCid());

      assertEquals("CHECK1", control.getEvidence());

      assertEquals("Failed", control.getStatus());

      assertEquals("Status of the 'aixmibd' setting within the '/etc/rc.tcpip' file on the host", control.getStatement());

      assertEquals(
            "The 'aixmibd' daemon controls many MIB variables. However, if this host is not using SNMP, the 'aixmibd' setting should be DISABLED to reduce risks of exploitation. Otherwise, the 'aixmibd' setting should be carefully configured according to the needs of the business.",
            control.getRationale());

      assertEquals("SERIOUS", control.getCriticalityLabel());

      assertEquals("3", control.getCriticalityValue());

      assertNotNull(control.getCheck());

      assertEquals(
            "The following String value(s) <B>X</B> indicate the current status of the <B>aixmibd</B> setting within the <B>/etc/rc.tcpip</B> file on the host. NOTE: If this host does not use SNMP, the 'aixmibd' setting should be DISABLED, according to the CIS Benchmark.",
            control.getCheck().getDescription());
   }

   // @Test
   // public void deveConverterREPORT_11850990() {
   //
   // new CompilanceReportConverter().convert(REPORT_11850990);
   //
   // assertTrue(Boolean.TRUE);
   // }
   //
   // @Test
   // public void deveConverterREPORT_11850960() {
   //
   // new CompilanceReportConverter().convert(REPORT_11850960);
   //
   // assertTrue(Boolean.TRUE);
   // }
   //
   // @Test
   // public void deveConverterREPORT_11850958() {
   //
   // new CompilanceReportConverter().convert(REPORT_11850958);
   //
   // assertTrue(Boolean.TRUE);
   // }
   //
   // @Test
   // public void deveConverterREPORT_11850932() {
   //
   // final CompilanceReport report = new CompilanceReportConverter().convert(REPORT_11850932);
   //
   // final Host host = report.getHosts().get("10.0.40.19");
   //
   // final Control control = host.getControls().get("1072");
   //
   // assertEquals("CHECK13", control.getEvidence());
   //
   // }

}
