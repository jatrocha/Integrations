package com.modulo.integrations.commons.qualys.converter;

import java.util.Map;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.commons.qualys.to.QPCEventKey;
import com.modulo.riskmanager.client.to.event.Event;

public class EventToQPCEventKeyConverter implements Converter<Event, QPCEventKey> {

	@Override
	public QPCEventKey convert(Event source) {

		if ((source == null) || source.getCustomAttributes().isEmpty()) {

			throw new IllegalArgumentException();
		}

		final Map<String, Object> attributes = source.getCustomAttributes();

		return new QPCEventKey().withCid(attributes.get("cid").toString())
				.withPolicy(attributes.get("policy_name").toString()).withIp(attributes.get("ip_qpc").toString());
	}

}
