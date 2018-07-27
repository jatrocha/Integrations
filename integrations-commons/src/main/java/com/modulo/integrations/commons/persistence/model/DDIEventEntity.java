package com.modulo.integrations.commons.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Table(name = "ddi_event")
@Entity
public class DDIEventEntity extends AbstractEventEntity {

   private static final long serialVersionUID = -8804904462092637590L;

   private String applicationPort;

   private String applicationProtocol;

   private String firstDiscoveryDate;

   private String hostname;

   private String ipaddress;

   private String manuallyAdded;

   private String networkProtocol;

   private String parentIp;

   private String vulnDataWasTruncated;

   private String vulnerabilityId;

   private String vulnerabilitySolution;

   private String vulnerabilityStatus;

   private String vulnerabilityTypeId;

   private String cvssScore;

   private String riskLevel;

   private String operatingSystem;

   private String port;

   private String lastFixedDate;

   private String vulnerabilityDescription;

   /**
    * @return identificador do registro.
    */
   @Override
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ddi_event_id", nullable = false, unique = true)
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

   public String getApplicationPort() {
      return this.applicationPort;
   }

   public void setApplicationPort(String applicationPort) {
      this.applicationPort = applicationPort;
   }

   public String getApplicationProtocol() {
      return this.applicationProtocol;
   }

   public void setApplicationProtocol(String applicationProtocol) {
      this.applicationProtocol = applicationProtocol;
   }

   public String getFirstDiscoveryDate() {
      return this.firstDiscoveryDate;
   }

   public void setFirstDiscoveryDate(String firstDiscoveryDate) {
      this.firstDiscoveryDate = firstDiscoveryDate;
   }

   public String getHostname() {
      return this.hostname;
   }

   public void setHostname(String hostname) {
      this.hostname = hostname;
   }

   public String getIpaddress() {
      return this.ipaddress;
   }

   public void setIpaddress(String ipaddress) {
      this.ipaddress = ipaddress;
   }

   public String getManuallyAdded() {
      return this.manuallyAdded;
   }

   public void setManuallyAdded(String manuallyAdded) {
      this.manuallyAdded = manuallyAdded;
   }

   public String getNetworkProtocol() {
      return this.networkProtocol;
   }

   public void setNetworkProtocol(String networkProtocol) {
      this.networkProtocol = networkProtocol;
   }

   public String getParentIp() {
      return this.parentIp;
   }

   public void setParentIp(String parentIp) {
      this.parentIp = parentIp;
   }

   public String getVulnDataWasTruncated() {
      return this.vulnDataWasTruncated;
   }

   public void setVulnDataWasTruncated(String vulnDataWasTruncated) {
      this.vulnDataWasTruncated = vulnDataWasTruncated;
   }

   public String getVulnerabilityId() {
      return this.vulnerabilityId;
   }

   public void setVulnerabilityId(String vulnerabilityId) {
      this.vulnerabilityId = vulnerabilityId;
   }

   @Lob
   public String getVulnerabilitySolution() {
      return this.vulnerabilitySolution;
   }

   public void setVulnerabilitySolution(String vulnerabilitySolution) {
      this.vulnerabilitySolution = vulnerabilitySolution;
   }

   public String getVulnerabilityStatus() {
      return this.vulnerabilityStatus;
   }

   public void setVulnerabilityStatus(String vulnerabilityStatus) {
      this.vulnerabilityStatus = vulnerabilityStatus;
   }

   public String getVulnerabilityTypeId() {
      return this.vulnerabilityTypeId;
   }

   public void setVulnerabilityTypeId(String vulnerabilityTypeId) {
      this.vulnerabilityTypeId = vulnerabilityTypeId;
   }

   public String getCvssScore() {
      return this.cvssScore;
   }

   public void setCvssScore(String cvssScore) {
      this.cvssScore = cvssScore;
   }

   @Override
   public String getCode() {
      return this.code;
   }

   @Override
   public void setCode(String code) {
      this.code = code;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   @Lob
   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Integer getUrgency() {
      return this.urgency;
   }

   public void setUrgency(Integer urgency) {
      this.urgency = urgency;
   }

   public Integer getSeverity() {
      return this.severity;
   }

   public void setSeverity(Integer severity) {
      this.severity = severity;
   }

   public Integer getRelevance() {
      return this.relevance;
   }

   public void setRelevance(Integer relevance) {
      this.relevance = relevance;
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

   public String getRiskLevel() {
      return this.riskLevel;
   }

   public void setRiskLevel(String riskLevel) {
      this.riskLevel = riskLevel;
   }

   public String getOperatingSystem() {
      return this.operatingSystem;
   }

   public void setOperatingSystem(String operatingSystem) {
      this.operatingSystem = operatingSystem;
   }

   public String getPort() {
      return this.port;
   }

   public void setPort(String port) {
      this.port = port;
   }

   public void setLastFixedDate(String lastFixedDate) {
      this.lastFixedDate = lastFixedDate;
   }

   public String getLastFixedDate() {
      return this.lastFixedDate;
   }

   @Lob
   public String getVulnerabilityDescription() {
      return this.vulnerabilityDescription;
   }

   public void setVulnerabilityDescription(String vulnerabilityDescription) {
      this.vulnerabilityDescription = vulnerabilityDescription;
   }

}
