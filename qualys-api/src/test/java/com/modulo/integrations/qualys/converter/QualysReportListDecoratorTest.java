package com.modulo.integrations.qualys.converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Ignore;
import org.junit.Test;

import stubs.QPCReportsStub;

import com.modulo.integrations.qualys.to.QualysReportList;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class QualysReportListDecoratorTest {

   private static final String REPORT_LIST = QPCReportsStub.getReportList();

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveDecorarQuandoDocumentInvalido() {

      final Element header = null;

      new QualysReportListDecorator().decorate(header, new QualysReportList());

   }

   @Test
   @Ignore
   public void deveDecorarObjetoComDadosCabecalhoDoRelatorio() throws JDOMException, IOException {

      final Document document = new SAXBuilder().build(new ByteArrayInputStream(REPORT_LIST.getBytes()));

      final Element element = document.getRootElement().getChild("RESPONSE").getChild("REPORT_LIST").getChildren().iterator().next();

      final QualysReportList report = new QualysReportListDecorator().decorate(element, new QualysReportList());

      assertEquals("11950877", report.getId());

      assertEquals("2016-06-14T13:49:12Z", report.getLaunchDate());

      assertEquals("Solaris 11 Att Helena Stritch", report.getTitle());

      assertEquals("PDF", report.getOutputFormat());
   }

}
