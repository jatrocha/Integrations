package com.modulo.integrations.commons.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Table(name = "qpc_event")
@Entity
public class QPCEventEntity extends AbstractEventEntity {

   private static final long serialVersionUID = 1L;

   private String cid;

   private String os;

   private String ipQPC;

   private String policyName;

   private String lastScanDate;

   private String statusQPC;

   private String expectedEvidence;

   private String actualEvidence;

   private String extendedEvidence;

   private String controlReferences;

   private String criticalityLabel;

   private String criticalityValue;

   /**
    * @return identificador do registro.
    */
   @Override
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "qpc_event_id", nullable = false, unique = true)
   public Long getEventId() {
      return super.eventId;
   }

   /**
    * @param id atribui um valor ao identificador do registro.
    */
   @Override
   public void setEventId(Long id) {
      super.eventId = id;
   }

   public String getCid() {

      return this.cid;
   }

   public String getOs() {

      return this.os;
   }

   public String getIpQPC() {

      return this.ipQPC;
   }

   public String getPolicyName() {

      return this.policyName;
   }

   public String getLastScanDate() {

      return this.lastScanDate;
   }

   @Override
   public String getStatusQPC() {

      return this.statusQPC;
   }

   @Lob
   public String getExpectedEvidence() {

      return this.expectedEvidence;
   }

   @Lob
   public String getActualEvidence() {

      return this.actualEvidence;
   }

   @Lob
   public String getExtendedEvidence() {

      return this.extendedEvidence;
   }

   @Lob
   public String getControlReferences() {

      return this.controlReferences;
   }

   public void setCid(String cid) {
      this.cid = cid;

   }

   public void setOs(String os) {
      this.os = os;

   }

   public void setIpQPC(String ipQPC) {
      this.ipQPC = ipQPC;

   }

   public void setPolicyName(String policyName) {
      this.policyName = policyName;

   }

   public void setLastScanDate(String lastScanDate) {
      this.lastScanDate = lastScanDate;

   }

   @Override
   public void setStatusQPC(String statusQPC) {
      this.statusQPC = statusQPC;

   }

   public void setExpectedEvidence(String expectedEvidence) {
      this.expectedEvidence = expectedEvidence;

   }

   public void setActualEvidence(String actualEvidence) {
      this.actualEvidence = actualEvidence;

   }

   @Column(length = 3000)
   public void setExtendedEvidence(String extendedEvidence) {
      this.extendedEvidence = extendedEvidence;

   }

   public void setControlReferences(String controlReference) {
      this.controlReferences = controlReference;
   }

   @Lob
   public String getTitle() {

      return this.title;
   }

   @Lob
   public String getDescription() {

      return this.description;
   }

   public Integer getUrgency() {

      return this.urgency;
   }

   public Integer getSeverity() {

      return this.severity;
   }

   public Integer getRelevance() {

      return this.relevance;
   }

   public void setTitle(String title) {

      this.title = title;
   }

   public void setDescription(String description) {

      this.description = description;
   }

   public void setUrgency(Integer urgency) {

      this.urgency = urgency;

   }

   public void setSeverity(Integer severity) {

      this.severity = severity;

   }

   public void setRelevance(Integer relevance) {

      this.relevance = relevance;
   }

   @Override
   public String getCode() {
      return this.code;
   }

   @Override
   public void setCode(String code) {
      this.code = code;
   }

   @Override
   public String getComment() {
      return this.comment;
   }

   @Override
   public void setComment(String comment) {
      this.comment = comment;
   }

   @Override
   public Integer getStatus() {

      return this.status;
   }

   @Override
   public void setStatus(Integer status) {

      this.status = status;
   }

   public Integer getValue() {
      return this.value;
   }

   public void setValue(Integer value) {
      this.value = value;
   }

   public String getCriticalityLabel() {
      return this.criticalityLabel;
   }

   public String getCriticalityValue() {
      return this.criticalityValue;
   }

   public void setCriticalityLabel(String criticalityLabel) {

      this.criticalityLabel = criticalityLabel;
   }

   public void setCriticalityValue(String criticalityValue) {
      this.criticalityValue = criticalityValue;
   }

}
