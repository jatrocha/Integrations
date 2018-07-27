package com.modulo.integrations.commons.ddi.to;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class DDIEventKey implements Serializable {

   private static final long serialVersionUID = 5444111056481090358L;

   private static final String SPACER = "#";

   private String ipAddress;

   private String id;

   @Override
   public int hashCode() {

      return HashCodeBuilder.reflectionHashCode(this);
   }

   @Override
   public boolean equals(Object obj) {

      return EqualsBuilder.reflectionEquals(this, obj);
   }

   @Override
   public String toString() {

      final StringBuilder builder = new StringBuilder(this.ipAddress);

      builder.append(SPACER);

      builder.append(this.id);

      return builder.toString();
   }

   public String getIpAddress() {
      return this.ipAddress;
   }

   public void setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

}
