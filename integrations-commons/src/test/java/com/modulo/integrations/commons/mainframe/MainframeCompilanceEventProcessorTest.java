package com.modulo.integrations.commons.mainframe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.modulo.integrations.commons.persistence.model.AbstractEventEntity;
import com.modulo.integrations.commons.riskmanager.business.EventListBusiness;
import com.modulo.integrations.mainframe.service.MainframeCompilanceReportsService;
import com.modulo.integrations.mainframe.to.Control;
import com.modulo.integrations.mainframe.to.MainframeReport;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.EventFilter;
import com.modulo.riskmanager.client.to.event.Event;

public class MainframeCompilanceEventProcessorTest {

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
   public void naoDeveListarQuandoAuthNaoInformada() throws LackOfAuthorizationException, IOException {

      new MainframeCompilanceProcessor().with(null).process();

   }

   // @Test
   public void deveProcessar() throws LackOfAuthorizationException, IOException {

      final MainframeCompilanceReportsService reportsServiceMock = this.context.mock(MainframeCompilanceReportsService.class);

      final EventListBusiness eventListBusinessMock = this.context.mock(EventListBusiness.class);

      final EntityManagerFactory factoryMock = this.context.mock(EntityManagerFactory.class);

      final EntityManager entityManagerMock = this.context.mock(EntityManager.class);

      final List<MainframeReport> reports = this.getMainframeReport();

      final Map<String, Event> events = new HashMap<String, Event>();

      final AbstractEventEntity abstractEventEntity = new AbstractEventEntity();

      this.context.checking(new Expectations() {

         {

            this.one(reportsServiceMock).list();
            this.will(returnValue(reports));

            this.one(eventListBusinessMock).with(this.with(any(Auth.class)));
            this.will(returnValue(eventListBusinessMock));

            this.one(eventListBusinessMock).with(this.with(any(EventFilter.class)));
            this.will(returnValue(eventListBusinessMock));

            this.one(eventListBusinessMock).list();
            this.will(returnValue(events));

            this.one(factoryMock).createEntityManager();
            this.will(returnValue(entityManagerMock));

            this.one(entityManagerMock).getTransaction().begin();

            this.one(entityManagerMock).merge(this.with(abstractEventEntity));

            this.one(entityManagerMock).getTransaction().commit();

         }

      });

      final MainframeCompilanceProcessor processor = new MainframeCompilanceProcessor().with(new Auth());

      processor.setMainframeCompilanceReportsService(reportsServiceMock);

      processor.setEventListBusiness(eventListBusinessMock);

      processor.setEntityManagerFactory(factoryMock);

      processor.process();

      this.context.assertIsSatisfied();

   }

   // MainframeCompilanceReportService
   // recupera lista de relatorios (MainframeReport) //MainframeCompilanceReportsService
   // recupera lista de eventos no RM
   // indexa lista de eventos no RM
   // identifica a operacao: novo evento, atualizar evento ou fechar evento
   // grava no banco de dados
   // retorna lista de entidades

   private List<MainframeReport> getMainframeReport() {

      final List<MainframeReport> lista = new ArrayList<MainframeReport>();

      final MainframeReport report = new MainframeReport();

      report.setFilename("ZOS.xls");

      report.getControls().add(new Control());

      lista.add(report);

      return lista;
   }

}
