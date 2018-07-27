package com.modulo.integrations.qualys.converter;

import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.qualys.to.Check;

public class CheckListXPathConverter implements Converter<Document, Map<String, Check>> {

   private static final String EMPTY_SPACE = " ";

   @Override
   public Map<String, Check> convert(final Document source) {

      if (source == null) {

         throw new IllegalArgumentException("Arquivo invalido ou vazio.");
      }

      try {

         final XPath xPath = XPathFactory.newInstance().newXPath();

         final NodeList checks = (NodeList) xPath.evaluate("/COMPLIANCE_POLICY_REPORT/RESULTS/CHECKS/CHECK", source, XPathConstants.NODESET);

         final Map<String, Check> target = new HashMap<String, Check>();

         for (int i = 0; i < checks.getLength(); i++) {

            final Check check = new Check();

            final Node item = checks.item(i);

            for (int x = 0; x < item.getChildNodes().getLength(); x++) {

               if ("NAME".equalsIgnoreCase(item.getChildNodes().item(x).getNodeName())) {

                  check.setName(item.getChildNodes().item(x).getTextContent().trim());
               }

               if ("DP_NAME".equalsIgnoreCase(item.getChildNodes().item(x).getNodeName())) {

                  check.setDpName(item.getChildNodes().item(x).getTextContent().trim());
               }

               if ("EXPECTED".equalsIgnoreCase(item.getChildNodes().item(x).getNodeName())) {

                  if ("CRITERIA".equalsIgnoreCase(item.getChildNodes().item(x).getChildNodes().item(0).getNodeName())) {

                     check.setEvaluation(item.getChildNodes().item(x).getChildNodes().item(0).getChildNodes().item(0).getTextContent().trim());

                     if (item.getChildNodes().item(x).getChildNodes().item(0).getChildNodes().item(1) != null) {

                        check.setEvaluationValue(item.getChildNodes().item(x).getChildNodes().item(0).getChildNodes().item(1).getTextContent().trim());
                     }

                  } else {

                     final StringBuffer expected = new StringBuffer(item.getChildNodes().item(x).getChildNodes().item(0).getTextContent().trim());

                     expected.append(EMPTY_SPACE);

                     expected.append(item.getChildNodes().item(x).getAttributes().item(0).getTextContent().trim());

                     if (item.getChildNodes().item(x).getChildNodes().item(1) != null) {

                        expected.append(EMPTY_SPACE);

                        expected.append(item.getChildNodes().item(x).getChildNodes().item(1).getTextContent().trim());
                     }

                     check.setExpectedValue(expected.toString());

                     if ((item.getChildNodes().item(x).getChildNodes().item(2) != null)
                           && (item.getChildNodes().item(x).getChildNodes().item(2).getChildNodes().item(0) != null)) {

                        check.setEvaluation(item.getChildNodes().item(x).getChildNodes().item(2).getChildNodes().item(0).getTextContent().trim());
                     }
                  }
               }

               if ("ACTUAL".equalsIgnoreCase(item.getChildNodes().item(x).getNodeName()) && (item.getChildNodes().item(x).getChildNodes().item(0) != null)) {

                  check.setActualValue(item.getChildNodes().item(x).getChildNodes().item(0).getTextContent().trim());

               }

               if ("EXTENDED_EVIDENCE".equalsIgnoreCase(item.getChildNodes().item(x).getNodeName())) {

                  check.setExtendedEvidence(item.getChildNodes().item(x).getTextContent().replace('\n', ' ').replaceAll("\\s", ""));
               }

               target.put(check.getName(), check);
            }

         }

         return target;

      } catch (final XPathExpressionException e) {

         e.printStackTrace();

         throw new RuntimeException("Erro durante o processo de parse do arquivo.", e);
      }

   }
}
