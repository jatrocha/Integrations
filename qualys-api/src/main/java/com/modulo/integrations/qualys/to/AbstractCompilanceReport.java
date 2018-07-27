package com.modulo.integrations.qualys.to;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class AbstractCompilanceReport implements Serializable {

   private static final long serialVersionUID = -8491291583602178823L;

   private String name;

   private String policy;

   private String id;

   public String getPolicy() {

      return this.policy;
   }

   public void setName(final String name) {

      this.name = name;
   }

   public String getName() {

      return this.name;
   }

   public void setPolicy(final String policy) {
      this.policy = policy;
   }

   public String getId() {

      return this.id;
   }

   public void setId(final String id) {

      this.id = id;
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
