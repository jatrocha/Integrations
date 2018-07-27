package stubs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.mainframe.adapter.MainframeReportsServiceAdapter;

public class MainframeReportsServiceAdapterStub extends MainframeReportsServiceAdapter {

   @Override
   public Map<String, FileInputStream> list() throws FileNotFoundException {

      final Map<String, FileInputStream> reports = new HashMap<String, FileInputStream>();

      final String filePath = PropertiesUtil.getInstance().getString("Mainframe.Report.XLS.Stub");

      final String[] files = new File(filePath).list(new FilenameFilter() {

         @Override
         public boolean accept(File dir, String name) {

            return StringUtils.contains(name, "xlsx") && StringUtils.contains(name, "xls");
         }
      });

      for (final String fileName : files) {

         final FileInputStream fileInputStream = new FileInputStream(filePath.concat(fileName));

         reports.put(fileName, fileInputStream);

      }

      return reports;

   }

}
