package com.modulo.integrations.commons.mainframe;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Service;

import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.ComplianceProcessor;
import com.modulo.integrations.commons.mainframe.converter.EventToMainframeEventEntityConverter;
import com.modulo.integrations.commons.mainframe.converter.EventToMainframeEventKeyConverter;
import com.modulo.integrations.commons.mainframe.converter.MainframeControlToEventConverter;
import com.modulo.integrations.commons.mainframe.to.MainframeEventKey;
import com.modulo.integrations.commons.persistence.model.MainframeEventEntity;
import com.modulo.integrations.commons.riskmanager.business.EventListBusiness;
import com.modulo.integrations.mainframe.service.MainframeCompilanceReportsService;
import com.modulo.integrations.mainframe.to.Control;
import com.modulo.integrations.mainframe.to.MainframeReport;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.event.Event;

@Service(value = "mainframeCompilanceEventProcessor")
public class MainframeCompilanceProcessor extends ComplianceProcessor<MainframeEventKey, MainframeReport, MainframeEventEntity> {

   private Auth auth;

   @Resource
   private MainframeCompilanceReportsService reportsService;

   @Resource
   private EventListBusiness eventListBusiness;

   @Resource
   private EntityManagerFactory factory;

   public List<MainframeEventEntity> process() throws LackOfAuthorizationException, IOException {

      if (this.auth == null) {

         throw new IllegalArgumentException();
      }

      final Map<MainframeEventKey, Event> controlEvents = this.controlToEvents(this.reportsService.list());

      final Map<MainframeEventKey, Event> riskManagerEvents = this.getRiskManagerEvents();

      return super.processEntities(controlEvents, riskManagerEvents);
   }

   public MainframeCompilanceProcessor with(final Auth auth) {

      this.auth = auth;

      return this;
   }

   @Override
   protected final Map<MainframeEventKey, Event> indexRMEvents(Map<String, Event> rmEvents) {

      final Map<MainframeEventKey, Event> events = new HashMap<MainframeEventKey, Event>();

      for (final String key : rmEvents.keySet()) {

         final Event event = rmEvents.get(key);

         final MainframeEventKey eventKey = new EventToMainframeEventKeyConverter().convert(event);

         events.put(eventKey, event);

      }

      return events;
   }

   @Override
   protected Map<MainframeEventKey, Event> controlToEvents(List<MainframeReport> list) {

      final Map<MainframeEventKey, Event> events = new HashMap<MainframeEventKey, Event>();

      for (final MainframeReport compilanceReport : list) {

         for (final Control control : compilanceReport.getControls()) {

            final Event event = new MainframeControlToEventConverter().convert(control);

            final MainframeEventKey eventKey = new EventToMainframeEventKeyConverter().convert(event);

            events.put(eventKey, event);
         }

      }

      return events; // this.decorate(events, complete);
   }

   private Map<MainframeEventKey, Event> decorate(Map<MainframeEventKey, Event> events, Event complete) {

      for (final MainframeEventKey key : events.keySet()) {

         final Event event = events.get(key);

         event.getCustomAttributes().put("policy_name", complete.getCustomAttributes().get("policy_name"));

         event.getCustomAttributes().put("last_scan_date", complete.getCustomAttributes().get("last_scan_date"));

         event.getCustomAttributes().put("os", complete.getCustomAttributes().get("os"));

         event.setRelevance(Integer.valueOf(3));

         event.setSeverity(Integer.valueOf(3));

         event.setUrgency(Integer.valueOf(3));

         events.put(key, event);
      }

      return events;
   }

   @Override
   protected Boolean hasControlPassed(final Map<MainframeEventKey, Event> controlEvents, final MainframeEventKey key) {

      if (controlEvents.get(key).getCustomAttributes().get("status_qpc") == null) {

         return Boolean.FALSE;
      }

      return Boolean.FALSE;
      
      //return Boolean.valueOf("Compliant".equalsIgnoreCase(controlEvents.get(key).getCustomAttributes().get("status_qpc").toString()));
   }

   @Override
   protected final EventListBusiness getEventListBusiness() {

      return this.eventListBusiness;
   }

   @Override
   protected final Auth getAuth() {

      return this.auth;
   }

   @Override
   protected String getEventType() {

      return PropertiesUtil.getInstance().getString("Mainframe.EventType");
   }

   protected final void setMainframeCompilanceReportsService(final MainframeCompilanceReportsService reportsServiceMock) {

      this.reportsService = reportsServiceMock;
   }

   protected final void setEventListBusiness(final EventListBusiness eventListBusinessMock) {

      this.eventListBusiness = eventListBusinessMock;
   }

   @Override
   protected Converter<Event, MainframeEventEntity> getConverter() {

      return new EventToMainframeEventEntityConverter();
   }

   @Override
   protected EntityManagerFactory getFactory() {

      return this.factory;
   }

   @Override
   protected String getFailedComment() {

      return PropertiesUtil.getInstance().getString("Mainframe.Event.Update.Comment");
   }

   @Override
   protected String getPassedComment() {

      return PropertiesUtil.getInstance().getString("Mainframe.Event.Update.Comment");
   }

   protected final void setEntityManagerFactory(final EntityManagerFactory factory) {

      this.factory = factory;
   }

}