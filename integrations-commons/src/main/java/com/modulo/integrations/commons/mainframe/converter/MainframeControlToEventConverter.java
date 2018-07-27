package com.modulo.integrations.commons.mainframe.converter;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.mainframe.to.Control;
import com.modulo.riskmanager.client.to.event.Event;

public class MainframeControlToEventConverter implements Converter<Control, Event> {

   @Override
   public Event convert(Control source) {

      if (source == null) {

         throw new IllegalArgumentException();
      }

      final Event event = new Event();

      event.getCustomAttributes().put("policy_name", source.getPolicyName());

      event.getCustomAttributes().put("last_scan_date", source.getLastScanDate());

      event.getCustomAttributes().put("os", source.getOperatingSystem());

      event.getCustomAttributes().put("ref_no", source.getRuleRef());

      event.setTitle(source.getStatement());

      event.getCustomAttributes().put("status_qpc", source.getStatus());

      event.setDescription(source.getDescription());
      
      event.setUrgency(Integer.valueOf(3));
      
      event.setSeverity(Integer.valueOf(3));
      
      event.setRelevance(Integer.valueOf(3));
      
      event.setAssetsNames(source.getAsset());

      return event;
   }
}
