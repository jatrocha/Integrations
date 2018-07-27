package com.modulo.integrations.mainframe.converter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import br.com.cygnus.framework.log.Log;
import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.template.log.ILog;

import com.modulo.integrations.mainframe.enums.MainframeHeaderKeys;
import com.modulo.integrations.mainframe.exception.InvalidFileRuntimeException;
import com.modulo.integrations.mainframe.to.Control;
import com.modulo.integrations.mainframe.to.MainframeReport;

public class MainframeReportConverter implements Converter<XSSFSheet, MainframeReport> {

   private static final Map<MainframeHeaderKeys, Integer> HEADER = new HashMap<MainframeHeaderKeys, Integer>();

   /** Classe de log. */
   private static final ILog<MainframeReportConverter> LOG = Log.get(MainframeReportConverter.class);

   static {

      HEADER.put(MainframeHeaderKeys.POLICY_NAME, MainframeHeaderKeys.POLICY_NAME.getValue());

      HEADER.put(MainframeHeaderKeys.LAST_SCAN_DATE, MainframeHeaderKeys.LAST_SCAN_DATE.getValue());

      HEADER.put(MainframeHeaderKeys.OPERATING_SYSTEM, MainframeHeaderKeys.OPERATING_SYSTEM.getValue());

      HEADER.put(MainframeHeaderKeys.ASSET, MainframeHeaderKeys.ASSET.getValue());

      HEADER.put(MainframeHeaderKeys.RULE_REF, MainframeHeaderKeys.RULE_REF.getValue());

      HEADER.put(MainframeHeaderKeys.STATEMENT, MainframeHeaderKeys.STATEMENT.getValue());

      HEADER.put(MainframeHeaderKeys.STATUS, MainframeHeaderKeys.STATUS.getValue());

      HEADER.put(MainframeHeaderKeys.SEVERITY, MainframeHeaderKeys.SEVERITY.getValue());

      HEADER.put(MainframeHeaderKeys.CATEGORY, MainframeHeaderKeys.CATEGORY.getValue());

      HEADER.put(MainframeHeaderKeys.TYPE, MainframeHeaderKeys.TYPE.getValue());

      HEADER.put(MainframeHeaderKeys.DESCRIPTION, MainframeHeaderKeys.DESCRIPTION.getValue());

      HEADER.put(MainframeHeaderKeys.EVIDENCE, MainframeHeaderKeys.EVIDENCE.getValue());

      HEADER.put(MainframeHeaderKeys.EXPECTED, MainframeHeaderKeys.EXPECTED.getValue());

      HEADER.put(MainframeHeaderKeys.LAST_POLICY_UPDATE, MainframeHeaderKeys.LAST_POLICY_UPDATE.getValue());

   }

   @Override
   public MainframeReport convert(final XSSFSheet source) {

      if (source == null) {

         throw new IllegalArgumentException();
      }

      this.validate(source);

      final MainframeReport report = new MainframeReport();

      final Map<String, Integer> mapa = new HashMap<String, Integer>(18);

      for (int i = 0; i < source.getRow(0).getLastCellNum(); i++) {

         if ((source.getRow(0).getCell(i) != null) && StringUtils.isNotEmpty(source.getRow(0).getCell(i).toString())) {

            mapa.put(source.getRow(0).getCell(i).toString(), Integer.valueOf(i));
         }

      }

      Integer c = Integer.valueOf(1);

      while (Boolean.TRUE) {

         final XSSFRow row = source.getRow(c);

         if (null == row) {

            break;
         }

         final Control control = new RowToControlConverter().with(mapa).convert(row);

         if (!StringUtils.isEmpty(control.getStatement())) {

            report.getControls().add(control);
         }

         ++c;

      }

      return report;

   }

   protected final void validate(final XSSFSheet source) throws InvalidFileRuntimeException {

      final Set<Integer> keysFound = new HashSet<Integer>();

      final Row row = source.getRow(0);

      for (final MainframeHeaderKeys key : HEADER.keySet()) {

         if (null != row.getCell(key.getValue().intValue())) {

            keysFound.add(key.getValue());
         }

      }

      if (Integer.valueOf(HEADER.size()).compareTo(Integer.valueOf(keysFound.size())) != 0) {

         throw new InvalidFileRuntimeException("Cabecalho invalido");
      }

   }
}
