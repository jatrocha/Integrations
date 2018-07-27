package com.modulo.integrations.ddi.converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import br.com.cygnus.framework.log.Log;
import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.template.log.ILog;

import com.modulo.integrations.ddi.exception.ConvertException;
import com.modulo.integrations.ddi.to.Host;
import com.modulo.integrations.ddi.to.Vulnerability;
import com.modulo.integrations.ddi.to.VulnerabilityReport;

public class ReportConverter implements Converter<String, VulnerabilityReport> {

   /** Classe de log. */
   private static final ILog<ReportConverter> LOG = Log.get(ReportConverter.class);

   @Override
   public VulnerabilityReport convert(final String source) {

      if (StringUtils.isEmpty(source)) {

         throw new IllegalArgumentException();
      }

      final VulnerabilityReport report = new VulnerabilityReport();

      try {

         final List<Element> hostList = this.loadReport(source).getRootElement().getChildren();

         for (final Element hostElement : hostList) {

            final Host host = new ElementToHostConverter().convert(hostElement);

            final List<Element> vulnerabilities = hostElement.getChildren("foundVulnerabilityList");

            for (final Element element : vulnerabilities) {

               final Element child = element.getChild("foundVulnerability");

               if (child != null) {

                  host.getVulnerabilities().add(new ElementToVulnerabilityConverter().convert(child));
               }
            }

            report.getHosts().put(host.getName(), host);
         }

      } catch (JDOMException | IOException e) {

         LOG.error(e.getMessage());

         throw new ConvertException(e);
      }

      return report;
   }

   protected Document loadReport(final String source) throws JDOMException, IOException {

      return new SAXBuilder().build(new ByteArrayInputStream(source.getBytes()));
   }

}

class ElementToHostConverter implements Converter<Element, Host> {

   @Override
   public Host convert(Element source) {

      final Host host = new Host();

      host.setName(source.getChild("name").getText());

      host.setIpAddress(source.getChild("ipAddress").getText());

      host.setOperatingSystem(source.getChild("os").getText());

      host.setParentIp(source.getChild("parentIp").getText());

      return host;
   }

}

class ElementToVulnerabilityConverter implements Converter<Element, Vulnerability> {

   @Override
   public Vulnerability convert(Element source) {

      final Vulnerability vulnerability = new Vulnerability();

      vulnerability.setTitle(source.getChild("title").getText());

      vulnerability.setRiskLevel(source.getChild("riskLevel").getText());

      vulnerability.setStatus(source.getChild("status").getText());

      vulnerability.setFirstDiscoveryDate(source.getChild("firstDiscoveredDate").getChild("iso8601").getText());

      if (source.getChild("lastFixedDate") != null) {

         vulnerability.setLastFixedDate(source.getChild("lastFixedDate").getChild("iso8601").getText());
      }

      vulnerability.setManuallyAdded(source.getChild("manuallyAdded").getText());

      vulnerability.setCvssScore(source.getChild("cvssScore").getText());

      vulnerability.setId(source.getChild("vid").getText());

      vulnerability.setVunerabilityTypeId(source.getChild("vulnTypeId").getText());

      vulnerability.setData(source.getChild("vulnData").getText());

      vulnerability.setDataWasTruncated(source.getChild("vulnDataWasTruncated").getText());

      vulnerability.setSolution(source.getChild("solution").getText());

      vulnerability.setNetworkProtocol(source.getChild("service").getChild("networkProtocol").getText());

      vulnerability.setApplicationProtocol(source.getChild("service").getChild("applicationProtocol").getText());

      vulnerability.setPort(source.getChild("service").getChild("port").getText());

      return vulnerability;
   }

}