package com.modulo.integrations.qualys.to;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Host implements Serializable {

   private static final long serialVersionUID = 1887015344342632145L;

   private String lastScanDate;

   private String operatingSystem;

   private String ip;

   private Map<String, Control> controls = new HashMap<String, Control>();

   public Host(String ip) {

      this();

      this.ip = ip;
   }

   public Host() {

      super();
   }

   public String getLastScanDate() {

      return this.lastScanDate;
   }

   public String getOperatingSystem() {

      return this.operatingSystem;
   }

   public String getIp() {

      return this.ip;
   }

   public void setLastScanDate(final String lastScanDate) {

      this.lastScanDate = lastScanDate;
   }

   public void setOperatingSystem(final String operatingSystem) {

      this.operatingSystem = operatingSystem;
   }

   public void setIp(final String ip) {

      this.ip = ip;
   }

   public Map<String, Control> getControls() {

      return this.controls;
   }

   public void setControls(final Map<String, Control> controls) {

      this.controls = controls;
   }

   @Override
   public int hashCode() {

      return HashCodeBuilder.reflectionHashCode(this);
   }

   @Override
   public boolean equals(Object obj) {

      return EqualsBuilder.reflectionEquals((obj), this);
   }

   @Override
   public String toString() {

      return ToStringBuilder.reflectionToString(this);
   }

}
