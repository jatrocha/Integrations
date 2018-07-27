package com.modulo.integrations.commons.ddi.converter;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.commons.ddi.to.DDIEventKey;
import com.modulo.riskmanager.client.to.event.Event;

public class EventToDDIEventKeyConverter implements Converter<Event, DDIEventKey> {

   @Override
   public DDIEventKey convert(Event source) {

      if (source == null) {

         throw new IllegalArgumentException();
      }

      final DDIEventKey key = new DDIEventKey();

      key.setIpAddress(source.getCustomAttributes().get("ipaddress").toString());

      key.setId(source.getCustomAttributes().get("vulnerability_id").toString());

      return key;
   }

}
