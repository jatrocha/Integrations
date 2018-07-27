package com.modulo.integrations.commons.qualys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import stubs.QualysCompilanceReportStub;

import com.modulo.integrations.commons.riskmanager.business.EventListBusiness;
import com.modulo.integrations.qualys.exception.QualysBusinessException;
import com.modulo.integrations.qualys.service.QualysCompilanceReportsService;
import com.modulo.integrations.qualys.to.CompilanceReport;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.EventFilter;
import com.modulo.riskmanager.client.to.event.Event;

public class QualysCompilanceEventProcessorTest {

   private Mockery context;

   @Before
   public void init() {

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);

         }

      };
   }

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveListarQuandoAuthNaoInformada() throws QualysBusinessException, LackOfAuthorizationException {

      new QualysCompilanceProcessor().with(null).process();

   }

   // @Test
   public void deveListarQualysCompilanceReport() throws QualysBusinessException, LackOfAuthorizationException {

      final QualysCompilanceReportsService reportsServiceMock = this.context.mock(QualysCompilanceReportsService.class);

      final EventListBusiness eventListMock = this.context.mock(EventListBusiness.class);

      final CompilanceReport expected = QualysCompilanceReportStub.read();

      final List<CompilanceReport> reports = new ArrayList<CompilanceReport>(1);

      reports.add(expected);

      final Map<String, Event> events = new HashMap<String, Event>();

      this.context.checking(new Expectations() {

         {

            this.one(reportsServiceMock).list();
            this.will(returnValue(reports));

            this.one(eventListMock).with(this.with(any(Auth.class)));
            this.will(returnValue(eventListMock));

            this.one(eventListMock).with(this.with(any(EventFilter.class)));
            this.will(returnValue(eventListMock));

            this.one(eventListMock).list();
            this.will(returnValue(events));

         }

      });

      final QualysCompilanceProcessor processor = new QualysCompilanceProcessor().with(new Auth());

      processor.setReportService(reportsServiceMock);

      processor.setEventListBusiness(eventListMock);

      processor.process();

      this.context.assertIsSatisfied();

   }

}
