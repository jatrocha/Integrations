package com.modulo.integrations.ddi.to;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Host implements Serializable {

   private static final long serialVersionUID = 3384766001303379534L;

   private String name;

   private String ipAddress;

   private String operatingSystem;

   private final Set<Vulnerability> vulnerabilities = new HashSet<Vulnerability>();

   private String parentIp;

   public String getName() {

      return this.name;
   }

   public String getIpAddress() {

      return this.ipAddress;
   }

   public String getOperatingSystem() {

      return this.operatingSystem;
   }

   public Set<Vulnerability> getVulnerabilities() {

      return this.vulnerabilities;
   }

   public void setName(final String name) {

      this.name = name;
   }

   public void setIpAddress(final String ipAddress) {

      this.ipAddress = ipAddress;
   }

   public void setOperatingSystem(final String operatingSystem) {

      this.operatingSystem = operatingSystem;
   }

   public void setParentIp(final String parentIp) {

      this.parentIp = parentIp;
   }

   public String getParentIp() {
      return this.parentIp;
   }

}
