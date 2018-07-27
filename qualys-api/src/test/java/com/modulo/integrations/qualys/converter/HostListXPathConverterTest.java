package com.modulo.integrations.qualys.converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import stubs.QPCReportsStub;
import temp.Decorator;
import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.qualys.to.Control;
import com.modulo.integrations.qualys.to.Host;

import static junit.framework.Assert.assertEquals;

public class HostListXPathConverterTest {

   private static final String AIX_REPORT = QPCReportsStub.getReport11851007();

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoElementInvalidoNull() {

      final Converter<Document, Map<String, Host>> converter = new HostListXPathConverter();

      final Document source = null;

      converter.convert(source);

   }

   @Test
   public void deveConverterQuandoElementValido() throws JDOMException, IOException, SAXException, ParserConfigurationException {

      final Document source = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(AIX_REPORT.getBytes())));

      final Map<String, Host> hosts = new HostListXPathConverter().convert(source);

      final Host actual = hosts.get("10.4.20.17");

      assertEquals(Integer.valueOf(94), Integer.valueOf(hosts.size()));

      assertEquals("2016-05-29T01:06:23Z", actual.getLastScanDate());

      assertEquals("AIX 6.1", actual.getOperatingSystem());

      assertEquals("10.4.20.17", actual.getIp());

   }

   @Test
   public void deveSelecionarUmaEvidenciaQuandoMaisDeUmaEvidencia() {

      final String evidence = " {  { CHECK13 } AND { CHECK14 }  } ";

      final String expected = "CHECK13";

      final String actual = new ControlDecorator().getEvidence(evidence);

      assertEquals(expected, actual);
   }

   @Test
   public void deveSelecionarUmaEvidenciaQuandoUmaEvidencia() {

      final String evidence = "CHECK13";

      final String expected = "CHECK13";

      final String actual = new ControlDecorator().getEvidence(evidence);

      assertEquals(expected, actual);
   }

   @Test
   public void deveSelecionarUmaEvidenciaQuandoMaisDeUmaEvidenciaEUmNao() {

      final String evidence = "  NOT  {  { CHECK38 }  } AND { CHECK39 }  } ";

      final String expected = "CHECK39";

      final String actual = new ControlDecorator().getEvidence(evidence);

      assertEquals(expected, actual);
   }

}

class ControlDecorator implements Decorator<Element, Control> {

   @Override
   public Control decorate(final Element source, final Control target) {

      if (source == null) {

         throw new IllegalArgumentException();
      }

      target.setCid(source.getChild("CID").getTextTrim());

      target.setEvidence(this.getEvidence(source.getChild("EVIDENCE").getTextTrim()));

      target.setStatus(source.getChild("STATUS").getTextTrim());

      target.setStatement(source.getChild("STATEMENT").getTextTrim());

      target.setRationale(source.getChild("RATIONALE").getTextTrim());

      target.setCriticalityLabel(source.getChild("CRITICALITY").getChild("LABEL").getTextTrim());

      target.setCriticalityValue(source.getChild("CRITICALITY").getChild("VALUE").getTextTrim());

      target.setControlReferences(source.getChild("CONTROL_REFERENCES").getTextTrim());

      return target;
   }

   protected final String getEvidence(final String source) {

      final String TOKEN = "AND";

      if (!StringUtils.contains(source, TOKEN)) {

         return source;
      }

      final StringTokenizer tokenizer = new StringTokenizer(source, TOKEN);

      if (!StringUtils.contains(source, "NOT")) {

         return StringUtils.remove(StringUtils.remove(tokenizer.nextToken(), '{'), '}').trim();
      }

      return StringUtils.remove(StringUtils.remove(StringUtils.remove(source.split("AND")[1], '{'), '}'), "OT").trim();

   }
}
