package com.modulo.integrations.commons.ddi.converter;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.modulo.integrations.commons.persistence.model.DDIEventEntity;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.enums.AssetType;

import static org.junit.Assert.assertEquals;

public class DDIEventEntityToAssetConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoEntityInvalidoNull() {

      new DDIEventEntityToAssetConverter().convert(null);

   }

   @Test
   public void deveConverterQuandoEntityValido() {

      final DDIEventEntity expected = this.getEntity();

      final Asset actual = new DDIEventEntityToAssetConverter().convert(expected);

      assertEquals(expected.getHostname(), actual.getName());

      assertEquals(actual.getParentPerimeter().getId(), "ea2143a0-fb46-4b85-9d7a-8a630860b011");

      assertEquals(actual.getParentPerimeter().getCaption(), "DDI");

      assertEquals("TVM - Threat Management > Vulnerability Assessments > DDI", actual.getPath());

      assertEquals(AssetType.TECNOLOGIA.toString(), actual.getAssetType());

      assertEquals(expected.getIpaddress(), actual.getCollectionParameters().getHostAddress());

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

      entity.setVulnDataWasTruncated("no");

      entity.setVulnerabilitySolution("If this host is required for production, please upgrade the operating system and ensure it is fully patched.");

      entity.setNetworkProtocol("tcp");

      entity.setApplicationProtocol("unknown");

      return entity;
   }

}
