package com.modulo.integrations.commons.mainframe.converter;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.commons.persistence.model.MainframeEventEntity;
import com.modulo.riskmanager.client.to.event.Event;

public class EventToMainframeEventEntityConverter implements Converter<Event, MainframeEventEntity> {

   @Override
   public MainframeEventEntity convert(final Event source) {

      if (source == null) {

         throw new IllegalArgumentException();
      }

      final MainframeEventEntity entity = new MainframeEventEntity();

      entity.setPolicyName(source.getCustomAttributes().get("policy_name").toString());

      entity.setLastScanDate(source.getCustomAttributes().get("last_scan_date").toString());

      entity.setOs(source.getCustomAttributes().get("os").toString());

      entity.setControlReferences(source.getCustomAttributes().get("ref_no").toString());

      entity.setTitle(source.getTitle());

      entity.setStatusQPC(source.getCustomAttributes().get("status_qpc").toString());

      entity.setDescription(source.getDescription());

      entity.setUrgency(source.getUrgency());

      entity.setSeverity(source.getSeverity());

      entity.setRelevance(source.getRelevance());
      
      entity.setAssetNames(source.getAssetsNames());

      return entity;
   }

}
