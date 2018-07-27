package com.modulo.integrations.commons.qualys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Service;

import br.com.cygnus.framework.log.Log;
import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.template.log.ILog;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.commons.ComplianceProcessor;
import com.modulo.integrations.commons.persistence.model.QPCEventEntity;
import com.modulo.integrations.commons.qualys.converter.EventToQPCEventEntityConverter;
import com.modulo.integrations.commons.qualys.converter.EventToQPCEventKeyConverter;
import com.modulo.integrations.commons.qualys.converter.QualysControlToEventConverter;
import com.modulo.integrations.commons.qualys.to.QPCEventKey;
import com.modulo.integrations.commons.riskmanager.business.EventListBusiness;
import com.modulo.integrations.qualys.exception.QualysBusinessException;
import com.modulo.integrations.qualys.service.QualysCompilanceReportsService;
import com.modulo.integrations.qualys.to.CompilanceReport;
import com.modulo.integrations.qualys.to.Host;
import com.modulo.integrations.qualys.to.QualysReportList;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.event.Event;

@Service(value = "qualysCompilanceEventProcessor")
public class QualysCompilanceProcessor extends ComplianceProcessor<QPCEventKey, CompilanceReport, QPCEventEntity> {

   @Resource
   private QualysCompilanceReportsService reportsService;

   @Resource(name = "qpcEventListBusiness")
   private EventListBusiness eventListBusiness;

   @Resource
   private EntityManagerFactory factory;

   private Auth auth;

   private static final ILog<QualysCompilanceProcessor> LOG = Log.get(QualysCompilanceProcessor.class);

   public QualysCompilanceProcessor with(Auth auth) {

      this.auth = auth;

      return this;
   }

   public List<QPCEventEntity> process() throws LackOfAuthorizationException, QualysBusinessException {

      if (this.auth == null) {

         throw new IllegalArgumentException();
      }

      final List<QPCEventEntity> retorno = new ArrayList<QPCEventEntity>();

      final Map<QPCEventKey, Event> riskManagerEvents = this.getRiskManagerEvents();

      for (final QualysReportList qualysReport : this.reportsService.list()) {

         try {

            final CompilanceReport compilanceReport = this.reportsService.read(qualysReport.getId());

            if (compilanceReport != null) {

               final Map<QPCEventKey, Event> controlEvents = this.controlToEvents(compilanceReport);

               retorno.addAll(this.processEntities(controlEvents, riskManagerEvents));
            }

         } catch (final QualysBusinessException e) {

            LOG.info(e.getMessage());
         }
      }

      return retorno;
   }

   protected Map<QPCEventKey, Event> controlToEvents(final CompilanceReport compilanceReport) {

      final Map<QPCEventKey, Event> events = new HashMap<QPCEventKey, Event>();

      for (final String hostKey : compilanceReport.getHosts().keySet()) {

         final Host host = compilanceReport.getHosts().get(hostKey);

         for (final String controlKey : host.getControls().keySet()) {

            final Event event = new QualysControlToEventConverter().with(compilanceReport).with(host).convert(host.getControls().get(controlKey));

            final QPCEventKey qpcEventKey = new EventToQPCEventKeyConverter().convert(event);

            events.put(qpcEventKey, event);

         }
      }

      return events;
   }

   @Override
   protected Map<QPCEventKey, Event> indexRMEvents(Map<String, Event> rmEvents) {

      final Map<QPCEventKey, Event> events = new HashMap<QPCEventKey, Event>();

      for (final String key : rmEvents.keySet()) {

         final Event event = rmEvents.get(key);

         final QPCEventKey qpcEventKey = new EventToQPCEventKeyConverter().convert(event);

         events.put(qpcEventKey, event);

      }

      return events;
   }

   @Override
   protected String getEventType() {

      return PropertiesUtil.getInstance().getString("QPC.EventType");
   }

   @Override
   protected String getPassedComment() {

      return PropertiesUtil.getInstance().getString("QPC.Event.Passed.Comment");
   }

   @Override
   protected String getFailedComment() {

      return PropertiesUtil.getInstance().getString("QPC.Event.Failed.Comment");
   }

   @Override
   protected Converter<Event, QPCEventEntity> getConverter() {

      return new EventToQPCEventEntityConverter();
   }

   @Override
   protected Auth getAuth() {

      return this.auth;
   }

   @Override
   protected EventListBusiness getEventListBusiness() {

      return this.eventListBusiness;
   }

   @Override
   protected EntityManagerFactory getFactory() {

      return this.factory;
   }

   protected final void setReportService(final QualysCompilanceReportsService reportsService) {

      this.reportsService = reportsService;
   }

   protected final void setEventListBusiness(final EventListBusiness eventListBusiness) {

      this.eventListBusiness = eventListBusiness;
   }

   @Override
   @Deprecated
   protected Map<QPCEventKey, Event> controlToEvents(List<CompilanceReport> list) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected Boolean hasControlPassed(Map<QPCEventKey, Event> controlEvents, QPCEventKey key) {

      return Boolean.valueOf("Passed".equalsIgnoreCase(controlEvents.get(key).getCustomAttributes().get("status_qpc").toString()));
   }
}
