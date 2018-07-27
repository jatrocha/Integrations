package com.modulo.integrations.qualys.converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;

import temp.Decorator;
import br.com.cygnus.framework.template.business.converter.Converter;

import com.modulo.integrations.qualys.to.QualysReportList;

@Service(value = "qualysReportListConverter")
public class QualysReportListConverter implements Converter<String, List<QualysReportList>> {

   @Override
   public List<QualysReportList> convert(String source) {

      if (StringUtils.isEmpty(source)) {

         throw new IllegalArgumentException();
      }

      final List<QualysReportList> target = new ArrayList<QualysReportList>();

      try {

         final List<Element> list = this.getReportList(this.loadXML(source)).getChildren();

         for (final Element element : list) {

            try {

               if ((null != element.getChild("TITLE"))) {

                  final Boolean hasModuloInTitle = Boolean.valueOf(StringUtils.contains(element.getChild("TITLE").getTextTrim().toLowerCase(), "modulo"));

                  final Boolean isOutputFormatXML = Boolean.valueOf(element.getChild("OUTPUT_FORMAT").getTextTrim().toLowerCase().equals("xml"));

                  if (hasModuloInTitle && isOutputFormatXML) {

                     target.add(new QualysReportListDecorator().decorate(element, new QualysReportList()));
                  }

               }

            } catch (final NullPointerException e) {

               System.out.println();
            }
         }

      } catch (final JDOMException e) {

         e.printStackTrace();

      } catch (final IOException e) {

         e.printStackTrace();
      }

      return target;
   }

   protected final Document loadXML(final String source) throws JDOMException, IOException {

      return new SAXBuilder().build(new ByteArrayInputStream(source.getBytes()));
   }

   protected final Element getReportList(final Document document) {

      return document.getRootElement().getChild("RESPONSE").getChild("REPORT_LIST");
   }

}

class QualysReportListDecorator implements Decorator<Element, QualysReportList> {

   @Override
   public QualysReportList decorate(Element source, QualysReportList target) {

      if (null == source) {

         throw new IllegalArgumentException();
      }

      target.setId(source.getChild("ID").getTextTrim());

      target.setLaunchDate(source.getChild("LAUNCH_DATETIME").getTextTrim());

      target.setOutputFormat(source.getChild("OUTPUT_FORMAT").getTextTrim());

      target.setTitle(source.getChild("TITLE").getTextTrim());

      return target;
   }

}