package com.modulo.integrations.commons.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Table(name = "mainframe_event")
@Entity
public class MainframeEventEntity extends AbstractEventEntity {

	private static final long serialVersionUID = -3136330967152359037L;

	private String lastScanDate;

	private String os;

	private String policyName;

	private String statusQPC;

	private String controlReferences;

	/**
	 * @return identificador do registro.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mainframe_event_id", nullable = false, unique = true)
	public Long getEventId() {
		return super.eventId;
	}

	/**
	 * @param id
	 *            atribui um valor ao identificador do registro.
	 */
	public void setEventId(Long id) {
		super.eventId = id;
	}

	public String getLastScanDate() {
		return this.lastScanDate;
	}

	public void setLastScanDate(String lastScanDate) {
		this.lastScanDate = lastScanDate;
	}

	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getPolicyName() {
		return this.policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getStatusQPC() {
		return this.statusQPC;
	}

	public void setStatusQPC(String statusQPC) {
		this.statusQPC = statusQPC;
	}

	public String getControlReferences() {
		return this.controlReferences;
	}

	public void setControlReferences(String controlReferences) {
		this.controlReferences = controlReferences;
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

	public String getAssetNames() {
		return assetNames;
	}

	public void setAssetNames(String assetNames) {
		this.assetNames = assetNames;
	}

}
