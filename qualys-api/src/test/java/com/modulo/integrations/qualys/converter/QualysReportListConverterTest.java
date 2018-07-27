package com.modulo.integrations.qualys.converter;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.junit.Ignore;
import org.junit.Test;

import stubs.QPCReportsStub;

import com.modulo.integrations.qualys.to.QualysReportList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

import static org.junit.Assert.assertEquals;

public class QualysReportListConverterTest {

   private static final String REPORT_LIST = QPCReportsStub.getReportList();

   private static Document REPORT_DOCUMENT;

   // static {
   //
   // try {
   //
   // REPORT_DOCUMENT = new SAXBuilder().build(new ByteArrayInputStream(REPORT_LIST.getBytes()));
   //
   // } catch (final JDOMException e) {
   //
   // e.printStackTrace();
   //
   // } catch (final IOException e) {
   //
   // e.printStackTrace();
   // }
   //
   // }

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoArquivoInvalidoNull() {

      new QualysReportListConverter().convert(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoArquivoInvalidoVazio() {

      new QualysReportListConverter().convert(StringUtils.EMPTY);
   }

   @Test
   @Ignore
   public void deveConverterReportList() {

      final List<QualysReportList> reportList = new QualysReportListConverter().convert(REPORT_LIST);

      assertFalse(reportList.isEmpty());

      final QualysReportList report = reportList.iterator().next();

      assertEquals("11939658", report.getId());

      assertEquals("2016-06-13T09:31:10Z", report.getLaunchDate());

      assertEquals("RedHat 7 Modulo (New)", report.getTitle());

      assertEquals("XML", report.getOutputFormat());
   }

   @Test
   @Ignore
   public void deveCarregarXML() throws JDOMException, IOException {

      assertNotNull(new QualysReportListConverter().loadXML(REPORT_LIST));

   }

   @Test
   @Ignore
   public void deveCarregarElementoReportList() {

      assertNotNull(new QualysReportListConverter().getReportList(REPORT_DOCUMENT));
   }

}
