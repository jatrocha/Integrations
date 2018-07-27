package com.modulo.integrations.qualys.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Service;

import br.com.cygnus.framework.log.Log;
import br.com.cygnus.framework.template.log.ILog;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.qualys.adapter.QualysReportsServiceAdapter;
import com.modulo.integrations.qualys.converter.CompilanceReportXPathConverter;
import com.modulo.integrations.qualys.converter.QualysReportListConverter;
import com.modulo.integrations.qualys.exception.QualysBusinessException;
import com.modulo.integrations.qualys.to.CompilanceReport;
import com.modulo.integrations.qualys.to.QualysAuth;
import com.modulo.integrations.qualys.to.QualysReportList;
import com.modulo.integrations.qualys.utils.QualysReportListUtil;

@Service(value = "qualysCompilanceReportsService")
public class QualysCompilanceReportsService {

   @Resource
   private QualysReportsServiceAdapter adapter;

   @Resource
   private CompilanceReportXPathConverter xPathConverter;

   @Resource
   private QualysReportListConverter reportListConverter;

   @Resource
   private QualysReportListUtil reportUtil;

   /** Classe de log. */
   private static final ILog<QualysCompilanceReportsService> LOG = Log.get(QualysCompilanceReportsService.class);

   protected final void setAdapter(final QualysReportsServiceAdapter adapter) {

      this.adapter = adapter;
   }

   public List<QualysReportList> list() throws QualysBusinessException {

      try {

         final QualysAuth auth = new QualysAuth();

         final List<QualysReportList> convert = this.reportListConverter.convert(this.adapter.list(auth));

         for (final QualysReportList report : convert) {

            this.adapter.readAndSave(auth, report.getId());
         }

         return convert;

      } catch (final Exception e) {

         throw new QualysBusinessException(e);
      }

   }

   protected final String md5Hash(final byte[] bytes) throws NoSuchAlgorithmException, IOException {

      return DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(bytes));
   }

   public CompilanceReport read(final String reportId) throws QualysBusinessException {

      try {

         final String read = this.getFileContent(reportId);

         final String hash = this.md5Hash(read.getBytes());

         if (this.reportUtil.isNotProcessed(hash)) {

            final CompilanceReport compilanceReport = this.xPathConverter.convert(read);

            compilanceReport.setId(reportId);

            this.reportUtil.register(hash);

            return compilanceReport;
         }

         return null;

      } catch (final Exception e) {

         throw new QualysBusinessException(e);
      }

   }

   protected final String getFileContent(final String reportId) {

      try {

         final String basePath = PropertiesUtil.getInstance().getString("QPC.Report.XML.Stub");

         final String xmlPath = basePath.concat(reportId).concat(".xml");

         LOG.info("Retornando relatorio: ".concat(xmlPath));

         final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(xmlPath), "UTF-8"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         reader.close();

         return IOUtils.toString(new BOMInputStream(IOUtils.toInputStream(buffer.toString())), "UTF-8");

      } catch (final FileNotFoundException e) {

         e.printStackTrace();

      } catch (final IOException e) {

         e.printStackTrace();
      }

      return null;
   }

   protected final void setReportListConverter(final QualysReportListConverter reportListConverter) {

      this.reportListConverter = reportListConverter;
   }

   protected final void setReportListUtil(QualysReportListUtil reportUtil) {

      this.reportUtil = reportUtil;
   }

}
