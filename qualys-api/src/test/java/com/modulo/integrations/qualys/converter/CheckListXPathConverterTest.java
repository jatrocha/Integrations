package com.modulo.integrations.qualys.converter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.jdom2.JDOMException;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.modulo.integrations.qualys.to.Check;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class CheckListXPathConverterTest {

   private static String REPORT = null;

   static {

      try {

         final BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stubs/11851007.xml"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         CheckListXPathConverterTest.REPORT = buffer.toString();

         reader.close();

      } catch (final FileNotFoundException e) {

         e.printStackTrace();

      } catch (final IOException e) {

         e.printStackTrace();
      }

   }

   @Test
   public void deveConverterQuandoElementValido() throws JDOMException, IOException, XPathExpressionException, SAXException, ParserConfigurationException {

      final Document source = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(REPORT.getBytes())));

      final Map<String, Check> checks = new CheckListXPathConverter().convert(source);

      final Check actual = checks.get("CHECK1");

      assertEquals("CHECK1", actual.getName());

      assertEquals("regular expression match", actual.getEvaluation());

      assertEquals("start /usr/sbin/aixmibd \"$src_running\"", actual.getActualValue());

      assertEquals("Row1:Filename,PatternRow2:/etc/rc.tcpip,^[[:blank:]]*start[[:blank:]][[:blank:]]*/usr/sbin/aixmibd", actual.getExtendedEvidence());

      assertEquals("Setting not found OR File not found", actual.getExpectedValue());

      assertEquals("ax00.services.general.disable-aixmibd", actual.getDpName());

   }

   @Test
   public void deveConverterQuandoElementValidoEvaluationValue() throws JDOMException, IOException, XPathExpressionException, SAXException,
         ParserConfigurationException {

      final Document source = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(REPORT.getBytes())));

      final Map<String, Check> checks = new CheckListXPathConverter().convert(source);

      final Check actual = checks.get("CHECK376");

      assertEquals("CHECK376", actual.getName());

      assertEquals("regular expression match", actual.getEvaluation());

      assertEquals("^root:cron:drwxrwx---", actual.getEvaluationValue());

      assertEquals("root:cron:rwxrwx---:/var/spool/cron/crontabs", actual.getActualValue());

      assertNull(actual.getExtendedEvidence());

      assertNull(actual.getExpectedValue());
   }

   @Test
   public void deveConverterQuandoElementValido377() throws JDOMException, IOException, XPathExpressionException, SAXException, ParserConfigurationException {

      final Document source = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(REPORT.getBytes())));

      final Map<String, Check> checks = new CheckListXPathConverter().convert(source);

      final Check actual = checks.get("CHECK377");

      assertEquals("CHECK377", actual.getName());

      assertNull(actual.getEvaluation());

      assertNull(actual.getEvaluationValue());

      assertEquals("Access by System Accounts (0)", actual.getActualValue());

      assertNull(actual.getExtendedEvidence());

      assertEquals("'ftpaccess' file not found OR 'ftpd' binary not found", actual.getExpectedValue());
   }
}
