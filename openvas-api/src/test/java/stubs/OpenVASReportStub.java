package stubs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OpenVASReportStub {

   private static String report;

   static {

      try {

         final BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stubs/report-2d38574d-5b64-4e55-892b-d03c659fdb98.xml"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         OpenVASReportStub.report = buffer.toString();

         reader.close();

      } catch (final FileNotFoundException e) {

         e.printStackTrace();

      } catch (final IOException e) {

         e.printStackTrace();
      }

   }

   public static final String getReport() {

      return report;
   }

}
