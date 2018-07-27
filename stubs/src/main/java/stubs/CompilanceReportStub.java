package stubs;

import java.util.ArrayList;
import java.util.List;

import com.modulo.integrations.qualys.to.Check;
import com.modulo.integrations.qualys.to.CompilanceReport;
import com.modulo.integrations.qualys.to.Control;
import com.modulo.integrations.qualys.to.Host;

public class CompilanceReportStub {

   public static List<CompilanceReport> list() {

      final List<CompilanceReport> list = new ArrayList<CompilanceReport>(1);

      list.add(get());

      return list;

   }

   public static CompilanceReport get() {

      final CompilanceReport report = new CompilanceReport();

      report.setName("AIX Modulo (New)");

      report.setPolicy("AIX 6.1 (Integrity)");

      report.getHosts().put(getHost().getIp(), getHost());

      report.setId("11851007");

      return report;

   }

   public static Host getHost() {

      final Host host = new Host();

      host.setIp("10.4.20.17");

      host.setLastScanDate("2016-05-29T01:06:23Z");

      host.setOperatingSystem("AIX 6.1");

      host.getControls().put(getControl().getCid(), getControl());

      return host;
   }

   private static Check getCheck() {

      final Check check = new Check();

      check.setDescription("The following String value(s) <B>X</B> indicate the current status of the <B>aixmibd</B> setting within the <B>/etc/rc.tcpip</B> file on the host. NOTE: If this host does not use SNMP, the 'aixmibd' setting should be DISABLED, according to the CIS Benchmark.");

      check.setEvaluation("regular expression match");

      check.setActualValue("start /usr/sbin/aixmibd \"$src_running\"");

      check.setExtendedEvidence("Row 1:File name,PatternRow 2:/etc/rc.tcpip,^[[:blank:]]*start[[:blank:]][[:blank:]]*/usr/sbin/aixmibd");

      check.setExpectedValue("Setting not found OR File not found");

      check.setDpName("ax00.services.general.disable-aixmibd");

      return check;
   }

   public static Control getControl() {

      final Control control = new Control();

      control.setCid("8227");

      control.setEvidence("CHECK1");

      control.setStatus("Failed");

      control.setStatement("Status of the 'aixmibd' setting within the '/etc/rc.tcpip' file on the host");

      control
            .setRationale("The 'aixmibd' daemon controls many MIB variables. However, if this host is not using SNMP, the 'aixmibd' setting should be DISABLED to reduce risks of exploitation. Otherwise, the 'aixmibd' setting should be carefully configured according to the needs of the business.");

      control.setCriticalityLabel("SERIOUS");

      control.setCriticalityValue("3");

      control.setCheck(getCheck());

      control.setControlReferences("1.3.21");

      return control;
   }

}
