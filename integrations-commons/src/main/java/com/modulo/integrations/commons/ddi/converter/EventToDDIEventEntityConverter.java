package com.modulo.integrations.commons.ddi.converter;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.commons.persistence.model.DDIEventEntity;
import com.modulo.riskmanager.client.to.event.Event;

public class EventToDDIEventEntityConverter implements Converter<Event, DDIEventEntity> {

   @Override
   public DDIEventEntity convert(final Event source) {

      if (source == null) {

         throw new IllegalArgumentException();
      }

      final DDIEventEntity target = new DDIEventEntity();

      target.setTitle(source.getTitle());

      target.setRiskLevel(source.getCustomAttributes().get("risk_level").toString());

      target.setVulnerabilityStatus(source.getCustomAttributes().get("vulnerability_status").toString());

      target.setFirstDiscoveryDate(source.getCustomAttributes().get("first_discovery_date").toString());

      target.setLastFixedDate(source.getCustomAttributes().get("lastfixed").toString());

      target.setManuallyAdded(source.getCustomAttributes().get("manually_added").toString());

      target.setCvssScore(source.getCustomAttributes().get("cvssScore").toString());

      target.setVulnerabilityId(source.getCustomAttributes().get("vulnerability_id").toString());

      target.setVulnerabilityTypeId(source.getCustomAttributes().get("vulnerability_type_id").toString());

      target.setDescription(source.getDescription());

      target.setVulnerabilityDescription(source.getDescription());

      target.setVulnDataWasTruncated(source.getCustomAttributes().get("vuln_data_was_truncated").toString());

      target.setVulnerabilitySolution(source.getCustomAttributes().get("new_vulnerability_solution").toString());

      target.setNetworkProtocol(source.getCustomAttributes().get("network_protocol").toString());

      target.setApplicationProtocol(source.getCustomAttributes().get("application_protocol").toString());

      target.setPort(source.getCustomAttributes().get("application_port").toString());

      target.setIpaddress(source.getCustomAttributes().get("ipaddress").toString());

      target.setHostname(source.getCustomAttributes().get("hostname").toString());

      target.setOperatingSystem(source.getCustomAttributes().get("operating_system").toString());

      target.setParentIp(source.getCustomAttributes().get("parent_ip").toString());

      return target;
   }

}