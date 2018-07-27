package stubs;

import com.modulo.integrations.qualys.to.CompilanceReport;

public class QualysCompilanceReportStub {

   public static CompilanceReport read() {

      final CompilanceReport report = new CompilanceReport();

      report.setName("AIX Modulo (New)");

      return report;
   }

}
