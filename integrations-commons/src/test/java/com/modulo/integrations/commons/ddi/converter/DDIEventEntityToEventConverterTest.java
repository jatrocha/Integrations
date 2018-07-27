package com.modulo.integrations.commons.ddi.converter;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.persistence.model.DDIEventEntity;
import com.modulo.riskmanager.client.to.event.Event;

import static org.junit.Assert.assertEquals;

public class DDIEventEntityToEventConverterTest {

   final String EVENT_TYPE = PropertiesUtil.getInstance().getString("DDI.EventType");

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoEventInvalidoNull() {

      new DDIEventEntityToEventConverter().convert(null);
   }

   @Test
   public void deveConverterQuandoEventValido() {

      final DDIEventEntity expected = this.getEntity();

      final Event actual = new DDIEventEntityToEventConverter().convert(expected);

      assertEquals(expected.getTitle(), actual.getTitle());

      assertEquals(this.EVENT_TYPE, actual.getEventType());

      assertEquals(expected.getRiskLevel(), actual.getCustomAttributes().get("risk_level"));

      assertEquals(expected.getVulnerabilityStatus(), actual.getCustomAttributes().get("vulnerability_status"));

      assertEquals(expected.getFirstDiscoveryDate(), actual.getCustomAttributes().get("first_discovery_date"));

      assertEquals(expected.getManuallyAdded(), actual.getCustomAttributes().get("manually_added"));

      assertEquals(expected.getCvssScore(), actual.getCustomAttributes().get("cvssScore"));

      assertEquals(expected.getVulnerabilityId(), actual.getCustomAttributes().get("vulnerability_id"));

      assertEquals(expected.getVulnerabilityTypeId(), actual.getCustomAttributes().get("vulnerability_type_id"));

      assertEquals(expected.getDescription(), actual.getDescription());

      assertEquals(expected.getVulnerabilityDescription(), actual.getCustomAttributes().get("vulnerability_description"));

      assertEquals(expected.getVulnDataWasTruncated(), actual.getCustomAttributes().get("vuln_data_was_truncated"));

      assertEquals(expected.getVulnerabilitySolution(), actual.getCustomAttributes().get("new_vulnerability_solution"));

      assertEquals(expected.getNetworkProtocol(), actual.getCustomAttributes().get("network_protocol"));

      assertEquals(expected.getApplicationProtocol(), actual.getCustomAttributes().get("application_protocol"));

      assertEquals(expected.getApplicationPort(), actual.getCustomAttributes().get("application_port"));

      assertEquals(expected.getIpaddress(), actual.getCustomAttributes().get("ipaddress"));

      assertEquals(expected.getHostname(), actual.getCustomAttributes().get("hostname"));

      assertEquals(expected.getOperatingSystem(), actual.getCustomAttributes().get("operating_system"));

      assertEquals(expected.getParentIp(), actual.getCustomAttributes().get("parent_ip"));

      assertEquals(RiskLevelToSeverity.HIGH.getValue(), actual.getSeverity());
   }

   private DDIEventEntity getEntity() {

      final DDIEventEntity entity = new DDIEventEntity();

      entity.setIpaddress("204.76.30.119");

      entity.setHostname("crminterview.crm.aflac.com");

      entity.setOperatingSystem("Windows Server 2003");

      entity.setParentIp(StringUtils.EMPTY);

      entity.setTitle("Microsoft Windows Server 2003 End of Life");

      entity.setRiskLevel("high");

      entity.setVulnerabilityStatus("recurred");

      entity.setFirstDiscoveryDate("2015-11-16 05:34:47");

      entity.setManuallyAdded("0");

      entity.setCvssScore("7.6");

      entity.setVulnerabilityId("17691987");

      entity.setVulnerabilityTypeId("DDI");

      entity.setDescription("Support has ended for Windows Server 2003. This host should be immediately upgraded.");

      entity.setVulnerabilityDescription("Support has ended for Windows Server 2003. This host should be immediately upgraded.");

      entity.setVulnDataWasTruncated("no");

      entity.setVulnerabilitySolution("If this host is required for production, please upgrade the operating system and ensure it is fully patched.");

      entity.setNetworkProtocol("tcp");

      entity.setApplicationProtocol("unknown");

      return entity;
   }

}