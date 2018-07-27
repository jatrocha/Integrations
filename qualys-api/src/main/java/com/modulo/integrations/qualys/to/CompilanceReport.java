/**
 *
 */
package com.modulo.integrations.qualys.to;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CompilanceReport extends AbstractCompilanceReport implements Serializable {

   private static final long serialVersionUID = -2030108470996177966L;

   private Map<String, Host> hosts = new HashMap<String, Host>();

   public Map<String, Host> getHosts() {

      return this.hosts;
   }

   public void setHosts(final Map<String, Host> hosts) {

      this.hosts = hosts;
   }

}
