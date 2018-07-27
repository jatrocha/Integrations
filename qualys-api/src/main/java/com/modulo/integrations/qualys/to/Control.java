package com.modulo.integrations.qualys.to;

import java.io.Serializable;

public class Control implements Serializable {

   private static final long serialVersionUID = 7683393051361225592L;

   private String cid;

   private String evidence;

   private String status;

   private String statement;

   private String rationale;

   private String criticalityLabel;

   private String criticalityValue;

   private Check check;

   private String controlReferences;

   public String getCid() {

      return this.cid;
   }

   public String getEvidence() {

      return this.evidence;
   }

   public String getStatus() {

      return this.status;
   }

   public String getStatement() {

      return this.statement;
   }

   public String getRationale() {

      return this.rationale;
   }

   public String getCriticalityLabel() {

      return this.criticalityLabel;
   }

   public String getCriticalityValue() {

      return this.criticalityValue;
   }

   public void setCid(final String cid) {

      this.cid = cid;
   }

   public void setEvidence(final String evidence) {

      this.evidence = evidence;
   }

   public void setStatus(final String status) {

      this.status = status;
   }

   public void setStatement(final String statement) {

      this.statement = statement;
   }

   public void setRationale(final String rationale) {

      this.rationale = rationale;
   }

   public void setCriticalityLabel(final String criticalityLabel) {

      this.criticalityLabel = criticalityLabel;
   }

   public void setCriticalityValue(final String criticalityValue) {

      this.criticalityValue = criticalityValue;
   }

   public Check getCheck() {

      return this.check;
   }

   public final void setCheck(final Check check) {

      this.check = check;

   }

   public String getControlReferences() {

      return this.controlReferences;
   }

   public void setControlReferences(final String controlReferences) {

      this.controlReferences = controlReferences;
   }

}
