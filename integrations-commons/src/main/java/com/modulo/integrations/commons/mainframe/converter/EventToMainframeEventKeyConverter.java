package com.modulo.integrations.commons.mainframe.converter;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.commons.mainframe.to.MainframeEventKey;
import com.modulo.riskmanager.client.to.event.Event;

public class EventToMainframeEventKeyConverter implements Converter<Event, MainframeEventKey> {

   @Override
   public MainframeEventKey convert(Event source) {

      if (source == null) {

         throw new IllegalArgumentException();
      }

      final MainframeEventKey key = new MainframeEventKey();

      try { // TODO: remove-me

         key.setPolicyName(source.getCustomAttributes().get("policy_name").toString());

         key.setOperatingSystem(source.getCustomAttributes().get("os").toString());

         key.setRuleRef(source.getCustomAttributes().get("ref_no").toString());

      } catch (final NullPointerException e) {

      }

      return key;
   }

}
