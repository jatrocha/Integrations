package com.modulo.integrations.qualys.service;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import stubs.QualysReportListStub;

import com.modulo.integrations.qualys.adapter.QualysReportsServiceAdapter;
import com.modulo.integrations.qualys.converter.QualysReportListConverter;
import com.modulo.integrations.qualys.to.CompilanceReport;
import com.modulo.integrations.qualys.to.QualysAuth;
import com.modulo.integrations.qualys.to.QualysReportList;
import com.modulo.integrations.qualys.utils.QualysReportListUtil;

import static junit.framework.Assert.assertNotNull;

import static org.junit.Assert.assertNull;

public class QualysCompilanceReportsServiceTest {

   private Mockery context;

   @Before
   public void init() {

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);

         }

      };
   }

   @Test
   @Ignore
   public void deveListarRelatorios() throws Exception {

      final QualysReportsServiceAdapter adapterMock = this.context.mock(QualysReportsServiceAdapter.class);

      final QualysReportListConverter reportListConverterMock = this.context.mock(QualysReportListConverter.class);

      final String xmlReportList = QualysReportListStub.getXML();

      final List<QualysReportList> reportList = QualysReportListStub.list();

      this.context.checking(new Expectations() {

         {

            this.one(adapterMock).list(this.with(any(QualysAuth.class)));
            this.will(returnValue(xmlReportList));

            this.one(reportListConverterMock).convert(this.with(any(String.class)));
            this.will(returnValue(reportList));

         }

      });

      final QualysCompilanceReportsService business = new QualysCompilanceReportsService();

      business.setAdapter(adapterMock);

      business.setReportListConverter(reportListConverterMock);

      final List<QualysReportList> reports = business.list();

      assertNotNull(reports);

      this.context.assertIsSatisfied();
   }

   @Test
   public void test() {

   }

   @Test
   @Ignore
   public void naodDeveRecuperarRelatorioQuandoEsseAindaJaProcessado() throws Exception {

      final QualysReportsServiceAdapter adapterMock = this.context.mock(QualysReportsServiceAdapter.class);

      final QualysReportListUtil reportUtilMock = this.context.mock(QualysReportListUtil.class);

      this.context.checking(new Expectations() {

         {
            this.one(reportUtilMock).isNotProcessed(this.with(any(String.class)));
            this.will(returnValue(Boolean.FALSE));
         }

      });

      final QualysCompilanceReportsService business = new QualysCompilanceReportsService();

      business.setAdapter(adapterMock);

      business.setReportListUtil(reportUtilMock);

      final CompilanceReport actual = business.read("11851007");

      assertNull(actual);

      this.context.assertIsSatisfied();
   }

}
