package com.modulo.integrations.mainframe.converter;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Test;

import stubs.MainframeReportStub;

import com.modulo.integrations.mainframe.exception.InvalidFileRuntimeException;
import com.modulo.integrations.mainframe.to.MainframeReport;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class MainframeReportConverterTest {

   @Test(expected = IllegalArgumentException.class)
   public void naoDeveConverterQuandoArquivoInvalidoNull() {

      final XSSFSheet sheet = null;

      new MainframeReportConverter().convert(sheet);
   }

   @Test
   public void deveConverterQuandoArquivoValido() throws IOException {

      final XSSFSheet sheet = MainframeReportStub.getReportMainframe();

      final MainframeReport actual = new MainframeReportConverter().convert(sheet);

      assertNotNull(actual);

      assertFalse(actual.getControls().isEmpty());

   }

   @Test(expected = InvalidFileRuntimeException.class)
   public void naoDeveValidarCamposCabecalho() throws IOException, InvalidFileRuntimeException {

      new MainframeReportConverter().validate(MainframeReportStub.getReportZOS());

   }

}