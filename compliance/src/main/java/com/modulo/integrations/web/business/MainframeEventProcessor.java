package com.modulo.integrations.web.business;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.ddi.converter.DDIEventEntityToAssetConverter;
import com.modulo.integrations.commons.mainframe.converter.MainframeEventEntityToEventConverter;
import com.modulo.integrations.commons.persistence.model.MainframeEventEntity;
import com.modulo.riskmanager.client.adapter.EventServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.client.to.event.Event;
import com.modulo.riskmanager.enums.AssetType;

@Service(value = "mainframeEventProcessor")
public class MainframeEventProcessor extends SingleThreadedEventProcessor<MainframeEventEntity> {

	@Resource
	private EventServiceAdapter eventServiceAdapter;

	@Resource
	private EventBusiness eventBusiness;

	@Resource
	private EntityManagerFactory factory;

	@Resource
	private AssetBusiness assetBusiness;

	@Override
	public void process(List<MainframeEventEntity> events) throws LackOfAuthorizationException {

		for (final MainframeEventEntity entity : events) {

			final Event event = new MainframeEventEntityToEventConverter().convert(entity);

			Asset asset = null;

			if (StringUtils.isNotEmpty(event.getAssetsNames()) && !"General".equalsIgnoreCase(event.getAssetsNames())) {

				Asset temp = new Asset();

				temp.setName(event.getAssetsNames());

				temp.setAssetType(AssetType.TECNOLOGIA.toString());
				
				temp.setPath(PropertiesUtil.getInstance().getString("QPC.Asset.Perimeter.Name"));

				asset = this.assetBusiness.with(super.getAuth()).with(temp).process();
			}

			event.setEventType(this.getEventType());

			if (entity.getCode() == null) {

				event.setComment(PropertiesUtil.getInstance().getString("Mainframe.Event.Created.Comment"));

				this.eventBusiness.with(super.getAuth()).with(event).process();

				if (asset != null) {

					this.eventServiceAdapter.associate(super.getAuth(), event, asset);
				}
			}

			if (entity.getCode() != null) {

				if (entity.getStatus() == null) {

					event.setId(entity.getCode());

					event.setComment(entity.getComment());

					this.eventServiceAdapter.update(super.getAuth(), event);

				} else {

					event.setId(entity.getCode());

					event.setStatus(entity.getStatus().toString());

					event.setComment(entity.getComment());

					this.eventServiceAdapter.update(super.getAuth(), event);
				}
			}

			this.deleteEvent(entity);
		}
	}

	@Override
	protected void deleteEvent(MainframeEventEntity entity) {

		final EntityManager entityManager = this.factory.createEntityManager();

		final MainframeEventEntity find = entityManager.find(MainframeEventEntity.class, entity.getEventId());

		entityManager.getTransaction().begin();

		entityManager.remove(find);

		entityManager.getTransaction().commit();
	}

	@Override
	protected String getEventType() {

		return PropertiesUtil.getInstance().getString("Mainframe.EventType");
	}

}
