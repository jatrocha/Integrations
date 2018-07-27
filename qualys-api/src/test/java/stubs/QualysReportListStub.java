package stubs;

import java.util.ArrayList;
import java.util.List;

import com.modulo.integrations.qualys.to.QualysReportList;

public class QualysReportListStub {

   public static final List<QualysReportList> list() {

      final QualysReportList report = new QualysReportList();

      report.setId("11851007");

      report.setLaunchDate("");

      report.setTitle("");

      report.setOutputFormat("");

      final List<QualysReportList> reportList = new ArrayList<QualysReportList>(1);

      reportList.add(report);

      return reportList;
   }

   public static String getXML() {

      return "<XML>";
   }

}
