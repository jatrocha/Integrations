package com.modulo.integrations.commons.persistence.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.cygnus.framework.template.dao.entity.AbstractEntity;

public class AbstractEventEntity extends AbstractEntity {

	private static final long serialVersionUID = 5537269190480190333L;

	protected Long eventId = null;

	protected String code;

	protected String title;

	protected String description;

	protected Integer urgency;

	protected Integer severity;

	protected Integer relevance;

	protected String comment;

	protected Integer status;

	protected Integer value;

	private String statusQPC;

	protected String assetNames;

	/**
	 * Default construtor.
	 */
	public AbstractEventEntity() {

		super();
	}

	/**
	 * @return identificador do registro.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qpc_event_id", nullable = false, unique = true)
	public Long getEventId() {
		return this.eventId;
	}

	/**
	 * @param id
	 *            atribui um valor ao identificador do registro.
	 */
	public void setEventId(Long id) {
		this.eventId = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getStatus() {

		return this.status;
	}

	public void setStatus(Integer status) {

		this.status = status;
	}

	public String getStatusQPC() {
		return this.statusQPC;
	}

	public void setStatusQPC(String statusQPC) {
		this.statusQPC = statusQPC;
	}
}
