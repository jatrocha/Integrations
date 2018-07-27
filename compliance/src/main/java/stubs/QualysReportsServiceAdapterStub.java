package stubs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.com.cygnus.framework.log.Log;
import br.com.cygnus.framework.template.log.ILog;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.qualys.adapter.QualysReportsServiceAdapter;
import com.modulo.integrations.qualys.to.QualysAuth;

public class QualysReportsServiceAdapterStub extends QualysReportsServiceAdapter {

   /** Classe de log. */
   private static final ILog<QualysReportsServiceAdapterStub> LOG = Log.get(QualysReportsServiceAdapterStub.class);

   private static String reportList;

   static {

      try {

         final String xmlPath = PropertiesUtil.getInstance().getString("QPC.Report.XML.List.Stub");

         final BufferedReader reader = new BufferedReader(new FileReader(xmlPath));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         reportList = buffer.toString();

         reader.close();

      } catch (final FileNotFoundException e) {

         e.printStackTrace();

      } catch (final IOException e) {

         e.printStackTrace();
      }
   }

   @Override
   public String list(QualysAuth auth) throws Exception {

      LOG.info("Retornando Lista de Relatorios");

      return reportList;
   }

   @Override
   public void readAndSave(QualysAuth auth, String reportId) throws Exception {
   }

}
