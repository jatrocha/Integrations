package com.modulo.integrations.qualys.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.modulo.integrations.commons.persistence.model.QPCProcessedReportEntity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QualysReportListUtilTest {

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
   public void test() {

   }

   @Test(expected = IllegalArgumentException.class)
   @Ignore
   public void naoDeveProcessarQuandoReportIdNull() throws IOException {

      new QualysReportListUtil().isNotProcessed(null);
   }

   @Test(expected = IllegalArgumentException.class)
   @Ignore
   public void naoDeveProcessarQuandoReportIdEmpty() throws IOException {

      new QualysReportListUtil().isNotProcessed(StringUtils.EMPTY);
   }

   @Test
   @Ignore
   public void deveRetornarTrueQuandoReportNaoProcessadoEArquivoVazio() throws IOException {

      final EntityManager entityManagerMock = this.context.mock(EntityManager.class);

      final List<QPCProcessedReportEntity> processedReports = new ArrayList<QPCProcessedReportEntity>(0);

      this.context.checking(new Expectations() {

         {

            this.one(entityManagerMock).createQuery(this.with(any(String.class))).getResultList();
            this.will(returnValue(processedReports));

         }

      });

      assertTrue(new QualysReportListUtil().isNotProcessed("1001001"));
   }

   @Test
   @Ignore
   public void deveRetornarTrueReportNaoProcessado() throws IOException {

      final File file = new File("src/test/resources/stubs/ProcessedQPCReports.txt");

      assertTrue(new QualysReportListUtil().isNotProcessed("1001001"));
   }

   @Test
   @Ignore
   public void deveRetornarFalseReportProcessado() throws IOException {

      final File file = new File("src/test/resources/stubs/ProcessedQPCReports.txt");

      assertFalse(new QualysReportListUtil().isNotProcessed("1001002"));
   }

   @Test(expected = IllegalArgumentException.class)
   @Ignore
   public void naoDeveRegistrarProcessamentoQuandoIdEmpty() throws IOException {

      new QualysReportListUtil().register(null);
   }

   @Test
   @Ignore
   public void deveRegistrarProcessamento() throws IOException {

      final String id = "1001006";

      new QualysReportListUtil().register(id);

      final String contents = FileUtils.readFileToString(new File("src/test/resources/stubs/ProcessedQPCReports.txt"));

      assertTrue(StringUtils.contains(contents, id));
   }

}
