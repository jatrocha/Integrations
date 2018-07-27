package com.modulo.integrations.web.converter;

import org.junit.Test;

import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.qualys.to.Host;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.enums.AssetType;

import static junit.framework.Assert.assertEquals;

public class QualysHostToAssetConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoHostInvalidoNull() {

      new QualysHostToAssetConverter().convert(null);
   }

   @Test
   public void deveConverterQuandoHostValido() {

      final Converter<Host, Asset> converter = new QualysHostToAssetConverter();

      final Host source = new Host();

      source.setIp("10.4.20.17");

      final Asset target = converter.convert(source);

      assertEquals(source.getIp(), target.getName());

      assertEquals(AssetType.TECNOLOGIA.toString(), target.getAssetType());

      assertEquals(PropertiesUtil.getInstance().getString("QPC.Asset.Perimeter.Name"), target.getPath());

      assertEquals(Integer.valueOf(3), target.getRelevance());
   }

}
