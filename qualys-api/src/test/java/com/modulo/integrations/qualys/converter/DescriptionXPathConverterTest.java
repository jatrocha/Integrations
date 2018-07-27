package com.modulo.integrations.qualys.converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import stubs.QPCReportsStub;
import br.com.cygnus.framework.template.business.converter.Converter;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class DescriptionXPathConverterTest {

   private static final String AIX_REPORT = QPCReportsStub.getReport11851007();

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoElementInvalidoNull() {

      final Converter<Document, Map<String, String>> converter = new DescriptionXPathConverter();

      final Document source = null;

      converter.convert(source);
   }

   @Test
   public void deveConverterQuandoElementValido() throws JDOMException, IOException, SAXException, ParserConfigurationException {

      final Document source = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(AIX_REPORT.getBytes())));

      final Map<String, String> descriptions = new DescriptionXPathConverter().convert(source);

      final String key = "ax00.services.general.disable-aixmibd";

      final String actual = descriptions.get(key);

      assertEquals(
            "The following String value(s) <B>X</B> indicate the current status of the <B>aixmibd</B> setting within the <B>/etc/rc.tcpip</B> file on the host. NOTE: If this host does not use SNMP, the 'aixmibd' setting should be DISABLED, according to the CIS Benchmark.",
            actual);

   }

}
