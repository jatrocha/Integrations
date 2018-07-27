package com.modulo.integrations.commons.qualys.to;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class QPCEventKey {

   private static final String SPACER = "#";

   private String cid;

   private String policy;

   private String ip;

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

      final StringBuilder builder = new StringBuilder(this.cid);

      builder.append(SPACER);

      builder.append(this.policy);

      builder.append(SPACER);

      builder.append(this.ip);

      return builder.toString();
   }

   public QPCEventKey withCid(final String cid) {

      this.cid = cid;

      return this;
   }

   public QPCEventKey withPolicy(final String policy) {

      this.policy = policy;

      return this;
   }

   public QPCEventKey withIp(final String ip) {

      this.ip = ip;

      return this;
   }

}
