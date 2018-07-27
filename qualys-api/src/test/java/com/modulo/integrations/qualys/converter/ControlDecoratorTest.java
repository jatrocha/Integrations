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

import com.modulo.integrations.qualys.to.Control;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class ControlDecoratorTest {

   private static final String AIX_REPORT = QPCReportsStub.getReport11851007();

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveDecorarQuandoElementInvalido() {

      final Element element = null;

      new ControlDecorator().decorate(element, new Control());

   }

   @Test
   // @Ignore
   public void deveDecorarObjetoComDadosdoControl() throws JDOMException, IOException {

      final Document document = new SAXBuilder().build(new ByteArrayInputStream(AIX_REPORT.getBytes()));

      final Element element = document.getRootElement().getChild("RESULTS").getChild("HOST_LIST").getChildren().iterator().next().getChild("CONTROL_LIST")
            .getChildren().iterator().next();

      Control target = new Control();

      target = new ControlDecorator().decorate(element, target);

      assertEquals("8227", target.getCid());

      assertEquals("CHECK1", target.getEvidence());

      assertEquals("Failed", target.getStatus());

      assertEquals("Status of the 'aixmibd' setting within the '/etc/rc.tcpip' file on the host", target.getStatement());

      assertEquals(
            "The 'aixmibd' daemon controls many MIB variables. However, if this host is not using SNMP, the 'aixmibd' setting should be DISABLED to reduce risks of exploitation. Otherwise, the 'aixmibd' setting should be carefully configured according to the needs of the business.",
            target.getRationale());

      assertEquals("SERIOUS", target.getCriticalityLabel());

      assertEquals("3", target.getCriticalityValue());

      assertEquals("1.3.21", target.getControlReferences());

   }

   @Test
   @Ignore
   public void deveSelecionarUmaEvidenciaQuandoMaisDeUmaEvidencia() {

      final String evidence = " {  { CHECK13 } AND { CHECK14 }  } ";

      final String expected = "CHECK13";

      final String actual = new ControlDecorator().getEvidence(evidence);

      assertEquals(expected, actual);
   }

   @Test
   @Ignore
   public void deveSelecionarUmaEvidenciaQuandoUmaEvidencia() {

      final String evidence = "CHECK13";

      final String expected = "CHECK13";

      final String actual = new ControlDecorator().getEvidence(evidence);

      assertEquals(expected, actual);
   }

   @Test
   @Ignore
   public void deveSelecionarUmaEvidenciaQuandoMaisDeUmaEvidenciaEUmNao() {

      final String evidence = "  NOT  {  { CHECK38 }  } AND { CHECK39 }  } ";

      final String expected = "CHECK39";

      final String actual = new ControlDecorator().getEvidence(evidence);

      assertEquals(expected, actual);
   }

}
