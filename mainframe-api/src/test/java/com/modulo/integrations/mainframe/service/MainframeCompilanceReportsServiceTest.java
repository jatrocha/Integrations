package com.modulo.integrations.mainframe.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.modulo.integrations.mainframe.adapter.MainframeReportsServiceAdapter;
import com.modulo.integrations.mainframe.to.MainframeReport;

import static org.junit.Assert.assertFalse;

public class MainframeCompilanceReportsServiceTest {

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
   public void test() throws IOException {

      final MainframeReportsServiceAdapter serviceAdapterMock = this.context.mock(MainframeReportsServiceAdapter.class);

      final Map<String, FileInputStream> reports = new HashMap<String, FileInputStream>();

      reports.put("Mainframe TA Scan Results 2016.xlsx", new FileInputStream("src/test/resources/stubs/Mainframe TA Scan Results 2016.xlsx"));

      this.context.checking(new Expectations() {

         {

            this.one(serviceAdapterMock).list();
            this.will(returnValue(reports));

         }

      });

      final MainframeCompilanceReportsService reportsService = new MainframeCompilanceReportsService();

      reportsService.setServiceAdapter(serviceAdapterMock);

      final List<MainframeReport> actual = reportsService.list();

      assertFalse(actual.isEmpty());

      assertFalse(actual.iterator().next().getControls().isEmpty());

      this.context.assertIsSatisfied();

   }

}
