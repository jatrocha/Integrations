package com.modulo.integrations.commons.ddi.converter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.persistence.model.DDIEventEntity;
import com.modulo.riskmanager.client.to.event.Event;

public class DDIEventEntityToEventConverter implements Converter<DDIEventEntity, Event> {

   final String MAX_SIZE = PropertiesUtil.getInstance().getString("EVENT.DESCRIPTION.MAX.SIZE.CHAR");

   final String EVENT_TYPE = PropertiesUtil.getInstance().getString("DDI.EventType");

   @Override
   public Event convert(final DDIEventEntity source) {

      if (source == null) {

         throw new IllegalArgumentException();
      }

      final Event target = new Event();

      target.setTitle(source.getTitle());

      target.setEventType(this.EVENT_TYPE);

      target.getCustomAttributes().put("risk_level", source.getRiskLevel());

      target.getCustomAttributes().put("vulnerability_status", source.getVulnerabilityStatus());

      target.getCustomAttributes().put("first_discovery_date", source.getFirstDiscoveryDate());

      target.getCustomAttributes().put("lastfixed", source.getLastFixedDate());

      target.getCustomAttributes().put("manually_added", source.getManuallyAdded());

      target.getCustomAttributes().put("cvssScore", source.getCvssScore());

      target.getCustomAttributes().put("vulnerability_id", source.getVulnerabilityId());

      target.getCustomAttributes().put("vulnerability_type_id", source.getVulnerabilityTypeId());

      final Integer a = Integer.valueOf(this.MAX_SIZE);

      target.setDescription(StringUtils.substring(source.getDescription(), 0, a));

      target.getCustomAttributes().put("vulnerability_description", source.getVulnerabilityDescription());

      target.getCustomAttributes().put("vuln_data_was_truncated", source.getVulnDataWasTruncated());

      target.getCustomAttributes().put("new_vulnerability_solution", source.getVulnerabilitySolution());

      target.getCustomAttributes().put("network_protocol", source.getNetworkProtocol());

      target.getCustomAttributes().put("application_protocol", source.getApplicationProtocol());

      target.getCustomAttributes().put("application_port", source.getApplicationPort());

      target.getCustomAttributes().put("ipaddress", source.getIpaddress());

      target.getCustomAttributes().put("hostname", source.getHostname());

      target.getCustomAttributes().put("operating_system", source.getOperatingSystem());

      target.getCustomAttributes().put("parent_ip", source.getParentIp());

      target.setRelevance(Integer.valueOf(3));

      target.setSeverity(RiskLevelToSeverity.valueOf(source.getRiskLevel().toUpperCase()).getValue());

      target.setUrgency(Integer.valueOf(3));

      return target;
   }
}

enum RiskLevelToSeverity {

   INFO(1) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "info";
      }
   },
   INTERNAL(1) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "internal";
      }
   },
   LOW(2) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "low";
      }
   },
   MEDIUM(3) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "medium";
      }
   },
   HIGH(4) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "high";
      }
   },
   CRITICAL(5) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "critical";
      }
   };

   private final Integer value;

   private RiskLevelToSeverity(Integer value) {

      this.value = value;
   }

   public Integer getValue() {
      return this.value;
   }

   public Map<String, RiskLevelToSeverity> toMap() {

      final Map<String, RiskLevelToSeverity> map = new HashMap<String, RiskLevelToSeverity>();

      map.put("info", INFO);

      map.put("internal", INTERNAL);

      map.put("low", LOW);

      map.put("medium", MEDIUM);

      map.put("high", HIGH);

      map.put("critical", CRITICAL);

      return map;

   }

}