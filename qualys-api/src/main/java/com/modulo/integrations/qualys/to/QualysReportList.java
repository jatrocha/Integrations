package com.modulo.integrations.qualys.to;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class QualysReportList {

   private String id;

   private String launchDate;

   private String title;

   private String outputFormat;

   public String getId() {

      return this.id;
   }

   public String getLaunchDate() {

      return this.launchDate;
   }

   public String getTitle() {

      return this.title;
   }

   public String getOutputFormat() {

      return this.outputFormat;
   }

   public void setId(final String id) {

      this.id = id;
   }

   public void setLaunchDate(final String launchDate) {

      this.launchDate = launchDate;
   }

   public void setTitle(final String title) {

      this.title = title;
   }

   public void setOutputFormat(final String outputFormat) {

      this.outputFormat = outputFormat;
   }

   @Override
   public int hashCode() {

      return HashCodeBuilder.reflectionHashCode(this);
   }

   @Override
   public boolean equals(Object obj) {

      return ((QualysReportList) obj).getId().equals(this.getId());
   }

   @Override
   public String toString() {

      return ToStringBuilder.reflectionToString(this);
   }

}
