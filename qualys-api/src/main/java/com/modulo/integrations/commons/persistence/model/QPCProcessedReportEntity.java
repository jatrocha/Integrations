package com.modulo.integrations.commons.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "qpc_processed_report")
@Entity
public class QPCProcessedReportEntity implements Serializable {

   private static final long serialVersionUID = 1173789324460449451L;

   private String processedReportId;

   public QPCProcessedReportEntity(final String processedReportId) {

      this();

      this.processedReportId = processedReportId;
   }

   public QPCProcessedReportEntity() {

      super();
   }

   /**
    * @return identificador do registro.
    */
   @Id
   @Column(name = "qpc_processed_report_id", nullable = false, unique = true, length = 32)
   public String getProcessedReportId() {

      return this.processedReportId;
   }

   /**
    * @param id atribui um valor ao identificador do registro.
    */
   public void setProcessedReportId(final String processedReportId) {

      this.processedReportId = processedReportId;
   }
}
