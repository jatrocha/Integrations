package com.modulo.integrations.commons.riskmanager.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.modulo.integrations.commons.riskmanager.service.EventCountService;
import com.modulo.integrations.commons.riskmanager.service.EventListService;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.event.Event;

@Service(value = "qpcEventListBusiness")
public class QPCEventListBusiness extends EventListBusiness {

	@Resource
	private EventCountService eventCountService;

	@Resource
	private EventListService eventListService;

	@Override
	public Map<String, Event> list() throws LackOfAuthorizationException {

		if ((this.auth == null) || (this.filter == null)) {

			throw new IllegalArgumentException();
		}

		final Integer qtdeEventos = this.eventCountService.with(this.auth).with(this.filter).count();

		final Integer qtdeRequisicoes = this.getQtdeRequisicoes(qtdeEventos);

		Integer contador = Integer.valueOf(1);

		final Map<String, Event> retorno = new HashMap<String, Event>(qtdeEventos);

		while (contador <= qtdeRequisicoes) {

			final List<Event> list = this.eventListService.with(this.auth).with(this.filter.forPage(contador)).list();

			for (final Event event : list) {

				if (this.hasCid(event)) {

					retorno.put(event.getCode(), event);
				}
			}

			contador++;
		}

		return retorno;
	}

	private Boolean hasCid(Event event) {

		return (null != event.getCustomAttributes()) && null != event.getCustomAttributes().get("cid")
				&& !StringUtils.isEmpty(event.getCustomAttributes().get("cid").toString());
	}

}
