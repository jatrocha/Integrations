package com.modulo.integrations.qualys.converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.qualys.to.Check;
import com.modulo.integrations.qualys.to.CompilanceReport;
import com.modulo.integrations.qualys.to.Host;

@Service(value = "compilanceReportXPathConverter")
public class CompilanceReportXPathConverter implements Converter<String, CompilanceReport> {

   /**
    * @throws IllegalArgumentException caso parametro {@link source} for null ou vazio.
    */
   @Override
   public CompilanceReport convert(String source) {

      if (StringUtils.isEmpty(source)) {
         throw new IllegalArgumentException();
      }

      try {

         final String a = IOUtils.toString(new BOMInputStream(IOUtils.toInputStream(source)), "UTF-8");

         final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(a.getBytes())));

         final CompilanceReport retorno = this.loadReports(document);

         retorno.setHosts(this.loadChecks(document));

         return retorno;

      } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {

         e.printStackTrace();

         throw new RuntimeException("Erro durante o processo de parse do arquivo.", e);
      }

   }

   private CompilanceReport loadReports(final Document document) throws XPathExpressionException {

      final XPath xPath = XPathFactory.newInstance().newXPath();

      final NodeList headerList = (NodeList) xPath.evaluate("/COMPLIANCE_POLICY_REPORT/HEADER", document, XPathConstants.NODESET);

      final CompilanceReport compilanceReport = new CompilanceReport();

      if ("NAME".equalsIgnoreCase(headerList.item(0).getChildNodes().item(0).getNodeName())) {

         compilanceReport.setName(headerList.item(0).getChildNodes().item(0).getTextContent().trim());
      }

      if ("POLICY".equalsIgnoreCase(headerList.item(0).getChildNodes().item(4).getChildNodes().item(0).getNodeName())) {

         compilanceReport.setPolicy(headerList.item(0).getChildNodes().item(4).getChildNodes().item(0).getTextContent().trim());
      }

      return compilanceReport;

   }

   private Map<String, Host> loadChecks(final Document document) {

      final Map<String, Check> checkList = new CheckListXPathConverter().convert(document);

      final Map<String, Host> hosts = new HostListXPathConverter().convert(document);

      final Map<String, String> descriptions = new DescriptionXPathConverter().convert(document);

      final Set<String> ips = hosts.keySet();

      for (final String ip : ips) {

         final Host host = hosts.get(ip);

         final Set<String> controlNames = host.getControls().keySet();

         for (final String name : controlNames) {

            final Check check = checkList.get(host.getControls().get(name).getEvidence());

            check.setDescription(descriptions.get(check.getDpName()));

            host.getControls().get(name).setCheck(check);

            hosts.remove(host);

            hosts.put(ip, host);

         }
      }
      return hosts;

   }

}

// target.setPolicy(header.getChild("FILTERS").getChildText("POLICY"));
