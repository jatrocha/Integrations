package com.modulo.integrations.commons.mainframe.converter;

import org.apache.commons.lang.StringUtils;

import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.persistence.model.MainframeEventEntity;
import com.modulo.riskmanager.client.to.event.Event;

public class MainframeEventEntityToEventConverter implements Converter<MainframeEventEntity, Event> {

   final String MAX_SIZE = PropertiesUtil.getInstance().getString("EVENT.TITLE.MAX.SIZE.CHAR");

   @Override
   public Event convert(MainframeEventEntity source) {

      if (source == null) {

         throw new IllegalArgumentException();
      }

      final Event event = new Event();

      event.getCustomAttributes().put("policy_name", source.getPolicyName());

      event.getCustomAttributes().put("last_scan_date", source.getLastScanDate());

      event.getCustomAttributes().put("os", source.getOs());

      event.getCustomAttributes().put("ref_no", source.getControlReferences());

      final Integer a = Integer.valueOf(this.MAX_SIZE);

      event.setTitle(StringUtils.substring(source.getTitle(), 0, a));

      event.getCustomAttributes().put("status_qpc", source.getStatusQPC());

      event.setDescription(source.getDescription());

      event.setSeverity(source.getSeverity());

      event.setRelevance(source.getRelevance());

      event.setUrgency(source.getUrgency());
      
      event.setAssetsNames(source.getAssetNames());

      return event;
   }

}
