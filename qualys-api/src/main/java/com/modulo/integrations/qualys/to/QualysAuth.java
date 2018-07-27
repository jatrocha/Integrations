package com.modulo.integrations.qualys.to;

import br.com.cygnus.framework.util.PropertiesUtil;

public class QualysAuth {

   private final String token = PropertiesUtil.getInstance().getString("QPC.Token");

   public String getToken() {

      return this.token;
   }
}
