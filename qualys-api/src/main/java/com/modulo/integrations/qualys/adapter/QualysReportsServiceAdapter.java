package com.modulo.integrations.qualys.adapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import br.com.cygnus.framework.exception.EngineRuntimeException;
import br.com.cygnus.framework.log.Log;
import br.com.cygnus.framework.template.log.ILog;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.qualys.to.QualysAuth;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

@Service(value = "qualysReportsServiceAdapter")
public class QualysReportsServiceAdapter extends QualysRestServiceAdapter {

   private static final ILog<QualysReportsServiceAdapter> LOG = Log.get(QualysReportsServiceAdapter.class);

   private static final String AUTHORIZATION_KEY = "Authorization";

   private static final String SERVICE_URL = PropertiesUtil.getInstance().getString("QPC.Report.List");

   public String list(final QualysAuth auth) throws Exception {

      try {

         return this.getResponseData(
               this.getWebResource(SERVICE_URL.concat("?action=list")).header(AUTHORIZATION_KEY, "Basic ".concat(auth.getToken()))
                     .header("X-Requested-With", "List Reports").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class), new GenericType<String>() {
               });

      } catch (final EngineRuntimeException e) {

         final String description = e.getErrors().iterator().next().getDescription();

         throw new Exception(description, e);
      }
   }

   public void readAndSave(final QualysAuth auth, final String reportId) throws Exception {

      final String basePath = PropertiesUtil.getInstance().getString("QPC.Report.XML.Stub");

      HttpURLConnection connection = null;

      BufferedWriter bw = null;

      BufferedReader rd = null;

      try {

         final String filePath = basePath.concat(reportId).concat(".xml");

         if (!new File(filePath).exists()) {

            LOG.info("URL: " + SERVICE_URL.concat("?action=fetch&id=").concat(reportId));

            // Create connection
            final URL url = new URL(SERVICE_URL.concat("?action=fetch&id=").concat(reportId));

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("Authorization", "Basic ".concat(auth.getToken()));

            connection.setRequestProperty("X-Requested-With", "Download Report");

            connection.setUseCaches(false);

            connection.setDoOutput(true);

            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));

            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = null;

            while ((line = rd.readLine()) != null) {

               bw.write(line);

               bw.write("\r");
            }

         }

      } catch (final Exception e) {

         LOG.info(e.getMessage());

         throw new Exception(e);

      } finally {

         if (rd != null) {

            rd.close();
         }

         if (bw != null) {

            bw.close();
         }

         if (connection != null) {

            connection.disconnect();

         }
      }

   }
}
