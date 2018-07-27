package com.modulo.integrations.mainframe.to;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MainframeReport implements Serializable {

   private static final long serialVersionUID = -4211073688032956546L;

   private final Set<Control> controls = new HashSet<Control>();

   private String filename;

   public Set<Control> getControls() {

      return this.controls;
   }

   public void setFilename(final String filename) {

      this.filename = filename;
   }

   public String getFilename() {

      return this.filename;
   }

}
