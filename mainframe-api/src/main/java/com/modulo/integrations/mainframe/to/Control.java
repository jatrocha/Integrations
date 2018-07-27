package com.modulo.integrations.mainframe.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class Control implements Serializable {

   private static final long serialVersionUID = -5353180292355426978L;

   private String category;

   private String asset;

   private String lastScanDate;

   private String operatingSystem;

   private String policyName;

   private String ruleRef;

   private String status;

   private Integer severity;

   private String type;

   private String description;

   private String evidence;

   private String expected;

   private String lastPolicyUpdate;

   private String statement;

   public String getCategory() {
      return this.category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getAsset() {
      return this.asset;
   }

   public void setAsset(String asset) {
      this.asset = asset;
   }

   public String getLastScanDate() {
      return this.lastScanDate;
   }

   public void setLastScanDate(String lastScanDate) {
      this.lastScanDate = lastScanDate;
   }

   public String getOperatingSystem() {
      return this.operatingSystem;
   }

   public void setOperatingSystem(String operatingSystem) {
      this.operatingSystem = operatingSystem;
   }

   public String getPolicyName() {
      return this.policyName;
   }

   public void setPolicyName(String policyName) {
      this.policyName = policyName;
   }

   public String getRuleRef() {
      return this.ruleRef;
   }

   public void setRuleRef(String ruleRef) {
      this.ruleRef = ruleRef;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public Integer getSeverity() {
      return this.severity;
   }

   public void setSeverity(Integer severity) {
      this.severity = severity;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getEvidence() {
      return this.evidence;
   }

   public void setEvidence(String evidence) {
      this.evidence = evidence;
   }

   public String getExpected() {
      return this.expected;
   }

   public void setExpected(String expected) {
      this.expected = expected;
   }

   public String getLastPolicyUpdate() {
      return this.lastPolicyUpdate;
   }

   public void setLastPolicyUpdate(String lastPolicyUpdate) {
      this.lastPolicyUpdate = lastPolicyUpdate;
   }

   public String getStatement() {
      return this.statement;
   }

   public void setStatement(String statement) {
      this.statement = statement;
   }

   public Boolean isComplete() {

      return !StringUtils.isEmpty(this.getPolicyName()) && !StringUtils.isEmpty(this.getLastScanDate()) && !StringUtils.isEmpty(this.getOperatingSystem());
   }

}
