package stubs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DDIReportStub {

   private static String report;

   static {

      try {

         final BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stubs/Module Export Test Jan 21 2015 - portless ports excluded.xml"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         DDIReportStub.report = buffer.toString();

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
