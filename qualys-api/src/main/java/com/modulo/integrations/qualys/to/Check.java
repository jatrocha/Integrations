package com.modulo.integrations.qualys.to;

import java.io.Serializable;

public class Check implements Serializable {

   private static final long serialVersionUID = -4596322087169695898L;

   private String name;

   private String evaluation;

   private String actualValue;

   private String extendedEvidence;

   private String expectedValue;

   private String evaluationValue;

   private String dpName;

   private String description;

   public final String getName() {

      return this.name;
   }

   public final String getEvaluation() {

      return this.evaluation;
   }

   public final String getActualValue() {

      return this.actualValue;
   }

   public final String getExtendedEvidence() {

      return this.extendedEvidence;
   }

   public final String getExpectedValue() {

      return this.expectedValue;
   }

   public void setName(final String name) {

      this.name = name;
   }

   public void setEvaluation(final String evaluation) {

      this.evaluation = evaluation;
   }

   public void setActualValue(final String actualValue) {

      this.actualValue = actualValue;
   }

   public void setExtendedEvidence(final String extendedEvidence) {

      this.extendedEvidence = extendedEvidence;
   }

   public void setExpectedValue(final String expectedValue) {

      this.expectedValue = expectedValue;

   }

   public final String getEvaluationValue() {

      return this.evaluationValue;
   }

   public void setEvaluationValue(final String evaluationValue) {

      this.evaluationValue = evaluationValue;
   }

   public String getDpName() {

      return this.dpName;
   }

   public void setDpName(final String dpName) {

      this.dpName = dpName;
   }

   public String getDescription() {

      return this.description;
   }

   public void setDescription(final String description) {

      this.description = description;
   }

}
