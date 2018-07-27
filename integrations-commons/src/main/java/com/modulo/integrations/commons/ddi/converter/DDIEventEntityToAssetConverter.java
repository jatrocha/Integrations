package com.modulo.integrations.commons.ddi.converter;

import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.persistence.model.DDIEventEntity;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.client.to.asset.ParentPerimeter;
import com.modulo.riskmanager.enums.AssetType;

public class DDIEventEntityToAssetConverter implements Converter<DDIEventEntity, Asset> {

   @Override
   public Asset convert(final DDIEventEntity source) {

      if (source == null) {

         throw new IllegalArgumentException();
      }

      final Asset target = new Asset();

      target.setName(source.getHostname());

      final String parentPerimeterId = PropertiesUtil.getInstance().getString("DDI.Asset.ParentPerimeter.Id");

      final String parentPerimeterCaption = PropertiesUtil.getInstance().getString("DDI.Asset.ParentPerimeter.Caption");

      final String path = PropertiesUtil.getInstance().getString("DDI.Asset.Path");

      target.setParentPerimeter(new ParentPerimeter(parentPerimeterId, parentPerimeterCaption));

      target.setPath(path);

      target.setAssetType(AssetType.TECNOLOGIA.toString());

      target.getCollectionParameters().setHostAddress(source.getIpaddress());

      return target;
   }
}
