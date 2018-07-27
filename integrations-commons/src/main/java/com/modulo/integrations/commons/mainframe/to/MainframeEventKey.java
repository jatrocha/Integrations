package com.modulo.integrations.commons.mainframe.to;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class MainframeEventKey implements Serializable {

   private static final String SPACER = "#";

   private static final long serialVersionUID = 2960377279681730735L;

   private String policyName;

   private String operatingSystem;

   private String ruleRef;

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

      final StringBuilder builder = new StringBuilder(this.policyName);

      builder.append(SPACER);

      builder.append(this.operatingSystem);

      builder.append(SPACER);

      builder.append(this.ruleRef);

      return builder.toString();
   }

   public String getPolicyName() {
      return this.policyName;
   }

   public void setPolicyName(String policyName) {
      this.policyName = policyName;
   }

   public String getOperatingSystem() {
      return this.operatingSystem;
   }

   public void setOperatingSystem(String operatingSystem) {
      this.operatingSystem = operatingSystem;
   }

   public String getRuleRef() {
      return this.ruleRef;
   }

   public void setRuleRef(String ruleRef) {
      this.ruleRef = ruleRef;
   }

}
