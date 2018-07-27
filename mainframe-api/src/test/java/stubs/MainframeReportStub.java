package stubs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.modulo.integrations.mainframe.to.Control;

public class MainframeReportStub {

   public static XSSFSheet getReportZOS() throws IOException {

      final File myFile = new File("src/test/resources/stubs/ZOS.xlsx");

      final FileInputStream fis = new FileInputStream(myFile);

      final XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

      final XSSFSheet sheet = myWorkBook.getSheetAt(0);

      myWorkBook.close();

      return sheet;

   }

   public static XSSFSheet getReportMainframe() throws IOException {

      final File myFile = new File("src/test/resources/stubs/Mainframe TA Scan Results 2016.xlsx");

      final FileInputStream fis = new FileInputStream(myFile);

      final XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

      final XSSFSheet sheet = myWorkBook.getSheetAt(1);

      myWorkBook.close();

      return sheet;

   }

   public static Control getControl() {

      final Control control = new Control();

      control.setPolicyName("zOS TA v2.5");

      control.setLastScanDate("01-Mar-2015");

      control.setOperatingSystem("zOS");

      control.setAsset(StringUtils.EMPTY);

      control.setRuleRef("1.1.1");

      control
      .setStatement("Access to APF authorise a program/library, change linklist or dynamically load an LPA module, must be restricted to Mainframe Systems Support only, namely:"
            + "\n\n"
            + "* Update or higher access to logical PARMLIB concatenation\n"
            + "* Access to MVS.SETPROG or MVS.SET.PROG commands\n"
            + "* Access to BPX.FILEATTR.APF or BPX.FILEATTR.PROGCTL resources in USS\n"
            + "* Access to CSVAPF, CSVDYNL and CSVDYLPA profiles in the FACILITY class");

      control.setStatus("Fail");

      control.setSeverity(null);

      control.setCategory(StringUtils.EMPTY);

      control.setType(StringUtils.EMPTY);

      control
            .setDescription("Access to APF authorise a program/library, change linklist or dynamically load an LPA module, must be restricted to Mainframe Systems Support only, namely:"
                  + "\n\n"
                  + "* Update or higher access to logical PARMLIB concatenation\n"
                  + "* Access to MVS.SETPROG or MVS.SET.PROG commands\n"
                  + "* Access to BPX.FILEATTR.APF or BPX.FILEATTR.PROGCTL resources in USS\n"
                  + "* Access to CSVAPF, CSVDYNL and CSVDYLPA profiles in the FACILITY class");

      control.setEvidence(StringUtils.EMPTY);

      control.setExpected(StringUtils.EMPTY);

      control.setLastPolicyUpdate(StringUtils.EMPTY);

      return control;

   }
}
