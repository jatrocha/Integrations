package stubs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QPCReportsStub {

   private static String report11851007;

   private static String report11850990;

   private static String report11850960;

   private static String report11850958;

   private static String report11850932;

   private static String reportList;

   static {

      try {

         BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stubs/ReportList.xml"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         QPCReportsStub.reportList = buffer.toString();

         reader.close();

      } catch (FileNotFoundException e) {

         e.printStackTrace();

      } catch (IOException e) {

         e.printStackTrace();
      }

      try {

         BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stubs/11851007.xml"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         QPCReportsStub.report11851007 = buffer.toString();

         reader.close();

      } catch (FileNotFoundException e) {

         e.printStackTrace();

      } catch (IOException e) {

         e.printStackTrace();
      }

      try {

         BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stubs/11851007.xml"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         QPCReportsStub.report11851007 = buffer.toString();

         reader.close();

      } catch (FileNotFoundException e) {

         e.printStackTrace();

      } catch (IOException e) {

         e.printStackTrace();
      }

      try {

         BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stubs/11851007.xml"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         QPCReportsStub.report11851007 = buffer.toString();

         reader.close();

      } catch (FileNotFoundException e) {

         e.printStackTrace();

      } catch (IOException e) {

         e.printStackTrace();
      }

      try {

         BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stubs/11850990.xml"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         QPCReportsStub.report11850990 = buffer.toString();

         reader.close();

      } catch (FileNotFoundException e) {

         e.printStackTrace();

      } catch (IOException e) {

         e.printStackTrace();
      }

      try {

         BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stubs/11850960.xml"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         QPCReportsStub.report11850960 = buffer.toString();

         reader.close();

      } catch (FileNotFoundException e) {

         e.printStackTrace();

      } catch (IOException e) {

         e.printStackTrace();
      }

      try {

         BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stubs/11850958.xml"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         QPCReportsStub.report11850958 = buffer.toString();

         reader.close();

      } catch (FileNotFoundException e) {

         e.printStackTrace();

      } catch (IOException e) {

         e.printStackTrace();
      }

      try {

         BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stubs/11850932.xml"));

         final StringBuffer buffer = new StringBuffer();

         while (reader.ready()) {

            buffer.append(reader.readLine().trim());
         }

         QPCReportsStub.report11850932 = buffer.toString();

         reader.close();

      } catch (FileNotFoundException e) {

         e.printStackTrace();

      } catch (IOException e) {

         e.printStackTrace();
      }

   }

   public static final String getReport11851007() {

      return report11851007;
   }

   public static final String getReport11850990() {

      return report11850990;
   }

   public static String getReport11850960() {

      return report11850960;
   }

   public static String getReport11850958() {

      return report11850958;
   }

   public static String getReport11850932() {

      return report11850932;
   }

   public static String getReportList() {

      return reportList;
   }

}
