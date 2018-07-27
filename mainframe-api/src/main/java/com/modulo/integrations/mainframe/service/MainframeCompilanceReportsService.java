package com.modulo.integrations.mainframe.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import br.com.cygnus.framework.log.Log;
import br.com.cygnus.framework.template.log.ILog;

import com.modulo.integrations.mainframe.adapter.MainframeReportsServiceAdapter;
import com.modulo.integrations.mainframe.converter.MainframeReportConverter;
import com.modulo.integrations.mainframe.to.MainframeReport;

@Service(value = "mainframeCompilanceReportService")
public class MainframeCompilanceReportsService {

   /** Classe de log. */
   private static final ILog<MainframeCompilanceReportsService> LOG = Log.get(MainframeCompilanceReportsService.class);

   @Resource
   private MainframeReportsServiceAdapter serviceAdapter;

   public List<MainframeReport> list() throws IOException {

      final List<MainframeReport> mainframeReports = new ArrayList<MainframeReport>();

      final Map<String, FileInputStream> reportFiles = this.serviceAdapter.list();

      for (final String key : reportFiles.keySet()) {

         try {

            final FileInputStream fileInputStream = reportFiles.get(key);

            final XSSFWorkbook myWorkBook = new XSSFWorkbook(fileInputStream);

            for (int i = 1; i < (myWorkBook.getNumberOfSheets()); i++) {

               LOG.info(myWorkBook.getSheetAt(i).getSheetName());

               mainframeReports.add(new MainframeReportConverter().convert(myWorkBook.getSheetAt(i)));

               myWorkBook.close();

            }

         } catch (final Exception e) {

            LOG.error(e.getMessage());
         }
      }

      return mainframeReports;
   }

   protected final void setServiceAdapter(final MainframeReportsServiceAdapter serviceAdapter) {

      this.serviceAdapter = serviceAdapter;

   }
}
