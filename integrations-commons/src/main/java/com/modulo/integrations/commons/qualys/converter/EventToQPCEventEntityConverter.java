package com.modulo.integrations.commons.qualys.converter;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.commons.persistence.model.QPCEventEntity;
import com.modulo.riskmanager.client.to.event.Event;

public class EventToQPCEventEntityConverter implements Converter<Event, QPCEventEntity> {

	@Override
	public QPCEventEntity convert(final Event source) {

		final QPCEventEntity target = new QPCEventEntity();

		target.setTitle(source.getTitle());

		target.setDescription(source.getDescription());

		target.setUrgency(source.getUrgency());

		target.setSeverity(source.getSeverity());

		target.setRelevance(source.getRelevance());

		target.setValue(source.getValue());

		target.setCid(source.getCustomAttributes().get("cid").toString());

		target.setOs(source.getCustomAttributes().get("os").toString());

		target.setIpQPC(source.getCustomAttributes().get("ip_qpc").toString());

		target.setPolicyName(source.getCustomAttributes().get("policy_name").toString());

		target.setLastScanDate(source.getCustomAttributes().get("last_scan_date").toString());

		target.setStatusQPC(source.getCustomAttributes().get("status_qpc").toString());

		if (null != source.getCustomAttributes().get("expected_evidence")) {

			target.setExpectedEvidence(source.getCustomAttributes().get("expected_evidence").toString());
		}

		if (null != source.getCustomAttributes().get("actual_evidence")) {

			target.setActualEvidence(source.getCustomAttributes().get("actual_evidence").toString());
		}

		if (null != source.getCustomAttributes().get("extended_evidence")) {

			target.setExtendedEvidence(source.getCustomAttributes().get("extended_evidence").toString());
		}

		if (null != source.getCustomAttributes().get("control_references")) {

			target.setControlReferences(source.getCustomAttributes().get("control_references").toString());
		}

		target.setCriticalityLabel(source.getCustomAttributes().get("qpc_criticality_label").toString());

		target.setCriticalityValue(source.getCustomAttributes().get("qpc_criticality_value").toString());

		return target;

	}

}
