package com.modulo.integrations.mainframe.converter;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.mainframe.enums.MainframeHeaderKeys;
import com.modulo.integrations.mainframe.to.Control;

public class RowToControlConverter implements Converter<Row, Control> {

   private Map<String, Integer> cabecalho;

   @Override
   public Control convert(Row source) {

      if (source == null) {

         throw new IllegalArgumentException();
      }

      final Control control = new Control();

      final Cell category = source.getCell(this.cabecalho.get(MainframeHeaderKeys.CATEGORY.toString()));

      control.setCategory(category != null ? category.toString() : StringUtils.EMPTY);

      final Cell asset = source.getCell(this.cabecalho.get(MainframeHeaderKeys.ASSET.toString()));

      control.setAsset(asset != null ? asset.toString() : StringUtils.EMPTY);

      final Cell lastScanDate = source.getCell(this.cabecalho.get(MainframeHeaderKeys.LAST_SCAN_DATE.toString()));

      control.setLastScanDate(lastScanDate != null ? lastScanDate.toString() : StringUtils.EMPTY);

      final Cell operatingSystem = source.getCell(this.cabecalho.get(MainframeHeaderKeys.OPERATING_SYSTEM.toString()));

      control.setOperatingSystem(operatingSystem != null ? operatingSystem.toString() : StringUtils.EMPTY);

      final Cell policyName = source.getCell(this.cabecalho.get(MainframeHeaderKeys.POLICY_NAME.toString()));

      control.setPolicyName(policyName != null ? policyName.toString() : StringUtils.EMPTY);

      final Cell ruleRef = source.getCell(this.cabecalho.get(MainframeHeaderKeys.RULE_REF.toString()));

      control.setRuleRef(ruleRef != null ? ruleRef.toString() : StringUtils.EMPTY);

      final Cell status = source.getCell(this.cabecalho.get(MainframeHeaderKeys.STATUS.toString()));

      control.setStatus(status != null ? status.toString() : StringUtils.EMPTY);

      final Cell severity = source.getCell(this.cabecalho.get(MainframeHeaderKeys.SEVERITY.toString()));

      control
            .setSeverity((severity != null) && StringUtils.isNotEmpty(severity.toString()) ? Integer.valueOf(Double.valueOf(severity.toString()).intValue()) : null);

      final Cell type = source.getCell(this.cabecalho.get(MainframeHeaderKeys.TYPE.toString()));

      control.setType(type != null ? severity.toString() : StringUtils.EMPTY);

      final Cell description = source.getCell(this.cabecalho.get(MainframeHeaderKeys.STATEMENT.toString()));

      control.setDescription(description != null ? description.toString() : StringUtils.EMPTY);

      final Cell evidence = source.getCell(this.cabecalho.get(MainframeHeaderKeys.EVIDENCE.toString()));

      control.setEvidence(evidence != null ? evidence.toString() : StringUtils.EMPTY);

      final Cell expected = source.getCell(this.cabecalho.get(MainframeHeaderKeys.EXPECTED.toString()));

      control.setExpected(evidence != null ? expected.toString() : StringUtils.EMPTY);

      final Cell lastPolicyUpdate = source.getCell(this.cabecalho.get(MainframeHeaderKeys.LAST_POLICY_UPDATE.toString()));

      control.setLastPolicyUpdate(lastPolicyUpdate != null ? lastPolicyUpdate.toString() : StringUtils.EMPTY);

      final Cell statement = source.getCell(this.cabecalho.get(MainframeHeaderKeys.STATEMENT.toString()));

      control.setStatement(statement != null ? statement.toString() : StringUtils.EMPTY);

      return control;
   }

   public Converter<Row, Control> with(Map<String, Integer> cabecalho) {

      this.cabecalho = cabecalho;

      return this;
   }

}
