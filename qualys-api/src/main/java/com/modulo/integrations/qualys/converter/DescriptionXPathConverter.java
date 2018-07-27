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

public class DescriptionXPathConverter implements Converter<Document, Map<String, String>> {

   @Override
   public Map<String, String> convert(final Document source) {

      if (source == null) {

         throw new IllegalArgumentException("Arquivo invalido ou vazio.");
      }

      try {

         final XPath xPath = XPathFactory.newInstance().newXPath();

         final NodeList descriptionList = (NodeList) xPath.evaluate("/COMPLIANCE_POLICY_REPORT/RESULTS/DP_DESCRIPTIONS/DP", source, XPathConstants.NODESET);

         final Map<String, String> target = new HashMap<String, String>();

         for (int i = 0; i < descriptionList.getLength(); i++) {

            final Node item = descriptionList.item(i);

            String name = null;

            String description = null;

            if ("DP_NAME".equalsIgnoreCase(item.getChildNodes().item(0).getNodeName())) {

               name = item.getChildNodes().item(0).getChildNodes().item(0).getTextContent().trim();
            }

            if ("DESCRIPTION".equalsIgnoreCase(item.getChildNodes().item(1).getNodeName())) {

               description = item.getChildNodes().item(1).getChildNodes().item(0).getTextContent().trim();
            }

            target.put(name, description);

         }

         return target;

      } catch (final XPathExpressionException e) {

         e.printStackTrace();

         throw new RuntimeException("Erro durante o processo de parse do arquivo.", e);
      }

   }

}
