package com.modulo.integrations.qualys.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.qualys.to.Control;
import com.modulo.integrations.qualys.to.Host;

public class HostListXPathConverter implements Converter<Document, Map<String, Host>> {

   @Override
   public Map<String, Host> convert(final Document source) {

      if (source == null) {

         throw new IllegalArgumentException("Arquivo invalido ou vazio.");
      }

      try {

         final XPath xPath = XPathFactory.newInstance().newXPath();

         final NodeList hosts = (NodeList) xPath.evaluate("/COMPLIANCE_POLICY_REPORT/RESULTS/HOST_LIST/HOST", source, XPathConstants.NODESET);

         final Map<String, Host> target = new HashMap<String, Host>();

         for (int i = 0; i < hosts.getLength(); i++) {

            final Host host = new Host();

            final Node item = hosts.item(i);

            for (int x = 0; x < item.getChildNodes().getLength(); x++) {

               if ("IP".equalsIgnoreCase(item.getChildNodes().item(x).getNodeName())) {

                  host.setIp(item.getChildNodes().item(x).getChildNodes().item(0).getTextContent().trim());
               }

               if ("OPERATING_SYSTEM".equalsIgnoreCase(item.getChildNodes().item(x).getNodeName())) {

                  host.setOperatingSystem(item.getChildNodes().item(x).getChildNodes().item(0).getTextContent().trim());
               }

               if ("LAST_SCAN_DATE".equalsIgnoreCase(item.getChildNodes().item(x).getNodeName())) {

                  host.setLastScanDate(item.getChildNodes().item(x).getChildNodes().item(0).getTextContent().trim());
               }

               if ("CONTROL_LIST".equalsIgnoreCase(item.getChildNodes().item(x).getNodeName())) {

                  for (int c = 0; c < item.getChildNodes().item(x).getChildNodes().getLength(); c++) {

                     final Node nodeControl = item.getChildNodes().item(x).getChildNodes().item(c);

                     final Control control = new Control();

                     for (int ci = 0; ci < nodeControl.getChildNodes().getLength(); ci++) {

                        if ("CID".equalsIgnoreCase(nodeControl.getChildNodes().item(ci).getNodeName())) {

                           control.setCid(nodeControl.getChildNodes().item(ci).getTextContent().trim());
                        }

                        if ("STATEMENT".equalsIgnoreCase(nodeControl.getChildNodes().item(ci).getNodeName())) {

                           control.setStatement(nodeControl.getChildNodes().item(ci).getTextContent().trim());
                        }

                        if ("CRITICALITY".equalsIgnoreCase(nodeControl.getChildNodes().item(ci).getNodeName())) {

                           control.setCriticalityLabel(nodeControl.getChildNodes().item(ci).getChildNodes().item(0).getTextContent().trim());

                           control.setCriticalityValue(nodeControl.getChildNodes().item(ci).getChildNodes().item(1).getTextContent().trim());
                        }

                        if ("CONTROL_REFERENCES".equalsIgnoreCase(nodeControl.getChildNodes().item(ci).getNodeName())) {

                           control.setControlReferences(nodeControl.getChildNodes().item(ci).getTextContent().trim());
                        }

                        if ("RATIONALE".equalsIgnoreCase(nodeControl.getChildNodes().item(ci).getNodeName())) {

                           control.setRationale(nodeControl.getChildNodes().item(ci).getTextContent().trim());
                        }

                        if ("STATUS".equalsIgnoreCase(nodeControl.getChildNodes().item(ci).getNodeName())) {

                           control.setStatus(nodeControl.getChildNodes().item(ci).getTextContent().trim());
                        }

                        if ("EVIDENCE".equalsIgnoreCase(nodeControl.getChildNodes().item(ci).getNodeName())) {

                           control.setEvidence(this.getEvidence(nodeControl.getChildNodes().item(ci).getTextContent().trim()));
                        }

                     }

                     host.getControls().put(control.getCid(), control);
                  }

               }

               target.put(host.getIp(), host);
            }
         }
         return target;

      } catch (final XPathExpressionException e) {

         throw new RuntimeException("Erro durante o processo de parse do arquivo.", e);
      }

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
