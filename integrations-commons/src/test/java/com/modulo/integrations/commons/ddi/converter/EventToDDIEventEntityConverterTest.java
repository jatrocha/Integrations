package com.modulo.integrations.commons.ddi.converter;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.modulo.integrations.commons.persistence.model.DDIEventEntity;
import com.modulo.integrations.ddi.to.Host;
import com.modulo.integrations.ddi.to.Vulnerability;
import com.modulo.riskmanager.client.to.event.Event;

public class EventToDDIEventEntityConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoEventInvalidoNull() {

      new EventToDDIEventEntityConverter().convert(null);
   }

   @Test
   @Ignore
   public void deveConverterQuandoEventValido() {

      final Host host = this.getHost();

      final Vulnerability vulnerability = host.getVulnerabilities().iterator().next();

      final Event expected = new VulnerabilityToEventConverter().with(host).convert(vulnerability);

      final DDIEventEntity actual = new EventToDDIEventEntityConverter().convert(expected);

      assertEquals(expected.getTitle(), actual.getTitle());

      assertEquals(expected.getCustomAttributes().get("risk_level").toString(), actual.getRiskLevel());

      assertEquals(expected.getCustomAttributes().get("vulnerability_status").toString(), actual.getVulnerabilityStatus());

      assertEquals(expected.getCustomAttributes().get("first_discovery_date").toString(), actual.getFirstDiscoveryDate());

      assertEquals(expected.getCustomAttributes().get("manually_added").toString(), actual.getManuallyAdded());

      assertEquals(expected.getCustomAttributes().get("cvssScore").toString(), actual.getCvssScore());

      assertEquals(expected.getCustomAttributes().get("vulnerability_id").toString(), actual.getVulnerabilityId());

      assertEquals(expected.getCustomAttributes().get("vulnerability_type_id").toString(), actual.getVulnerabilityTypeId());

      assertEquals(expected.getDescription(), actual.getDescription());

      assertEquals(expected.getCustomAttributes().get("vulnerability_description").toString(), actual.getVulnerabilityDescription());

      assertEquals(expected.getCustomAttributes().get("vuln_data_was_truncated").toString(), actual.getVulnDataWasTruncated());

      assertEquals(expected.getCustomAttributes().get("new_vulnerability_solution").toString(), actual.getVulnerabilitySolution());

      assertEquals(expected.getCustomAttributes().get("network_protocol").toString(), actual.getNetworkProtocol());

      assertEquals(expected.getCustomAttributes().get("application_protocol").toString(), actual.getApplicationProtocol());

      assertEquals(expected.getCustomAttributes().get("application_port").toString(), actual.getApplicationPort());

      assertEquals(expected.getCustomAttributes().get("ipaddress").toString(), actual.getIpaddress());

      assertEquals(expected.getCustomAttributes().get("hostname").toString(), actual.getHostname());

      assertEquals(expected.getCustomAttributes().get("operating_system").toString(), actual.getOperatingSystem());

      assertEquals(expected.getCustomAttributes().get("parent_ip").toString(), actual.getParentIp());
   }

   private Host getHost() {

      final Host expected = new Host();

      expected.setIpAddress("204.76.30.119");

      expected.setName("crminterview.crm.aflac.com");

      expected.setOperatingSystem("Windows Server 2003");

      expected.setParentIp(StringUtils.EMPTY);

      final Vulnerability vulnerability = new Vulnerability();

      vulnerability.setTitle("Microsoft Windows Server 2003 End of Life");

      vulnerability.setRiskLevel("high");

      vulnerability.setStatus("recurred");

      vulnerability.setFirstDiscoveryDate("2015-11-16 05:34:47");
      
      vulnerability.setLastFixedDate("2015-11-16 05:34:47");

      vulnerability.setManuallyAdded("0");

      vulnerability.setCvssScore("7.6");

      vulnerability.setId("17691987");

      vulnerability.setVunerabilityTypeId("DDI");

      vulnerability.setData("Support has ended for Windows Server 2003. This host should be immediately upgraded.");

      vulnerability.setDataWasTruncated("no");

      vulnerability.setSolution("If this host is required for production, please upgrade the operating system and ensure it is fully patched.");

      vulnerability.setNetworkProtocol("tcp");

      vulnerability.setApplicationProtocol("unknown");
      
      expected.getVulnerabilities().add(vulnerability);

      return expected;
   }

}
