package com.modulo.integrations.web.converter;

import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.qualys.to.Host;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.enums.AssetType;

public class QualysHostToAssetConverter implements Converter<Host, Asset> {

   @Override
   public Asset convert(final Host source) {

      if (null == source) {

         throw new IllegalArgumentException();
      }

      final Asset retorno = new Asset();

      retorno.setName(source.getIp());

      retorno.getCollectionParameters().setHostAddress(source.getIp());

      retorno.setRelevance(Integer.valueOf(3));

      retorno.setPath(PropertiesUtil.getInstance().getString("QPC.Asset.Perimeter.Name"));

      retorno.setAssetType(AssetType.TECNOLOGIA.toString());

      return retorno;
   }

}
