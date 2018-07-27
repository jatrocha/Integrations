package com.modulo.integrations.openvas.converter;

import org.apache.commons.lang.StringUtils;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.openvas.to.VulnerabilityReport;

public class ReportConverter implements Converter<String, VulnerabilityReport> {

   @Override
   public final VulnerabilityReport convert(final String source) {

      if (StringUtils.isEmpty(source)) {

         throw new IllegalArgumentException();
      }

      return null;
   }

}
