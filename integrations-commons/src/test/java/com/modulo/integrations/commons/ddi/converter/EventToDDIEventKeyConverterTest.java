package com.modulo.integrations.commons.ddi.converter;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.modulo.integrations.commons.ddi.to.DDIEventKey;
import com.modulo.integrations.ddi.to.Host;
import com.modulo.integrations.ddi.to.Vulnerability;
import com.modulo.riskmanager.client.to.event.Event;

import static org.junit.Assert.assertEquals;

public class EventToDDIEventKeyConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoEventInvalidoNull() {

      new EventToDDIEventKeyConverter().convert(null);
   }

   @Test
   public void deveConverterQuandoHostValido() {

      final Host host = this.getHost();

      final Vulnerability vulnerability = host.getVulnerabilities().iterator().next();

      final Event event = new VulnerabilityToEventConverter().with(host).convert(vulnerability);

      final DDIEventKey actual = new EventToDDIEventKeyConverter().convert(event);

      assertEquals(event.getCustomAttributes().get("ipaddress"), actual.getIpAddress());

      assertEquals(event.getCustomAttributes().get("vulnerability_id"), actual.getId());

      assertEquals("204.76.30.119#17691987", actual.toString());

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
