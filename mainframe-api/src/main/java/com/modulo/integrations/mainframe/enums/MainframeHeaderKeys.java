package com.modulo.integrations.mainframe.enums;

public enum MainframeHeaderKeys {

   POLICY_NAME(0) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Policy Name";
      }
   },
   LAST_SCAN_DATE(1) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Last Scan Date";
      }
   },
   OPERATING_SYSTEM(2) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Operating System";
      }
   },
   ASSET(3) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Asset";
      }
   },
   RULE_REF(4) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Rule Ref";
      }
   },
   STATEMENT(5) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Statement";
      }
   },
   STATUS(10) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Status";
      }
   },
   SEVERITY(11) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Severity";
      }
   },
   CATEGORY(12) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Category";
      }
   },
   TYPE(13) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Type";
      }
   },
   DESCRIPTION(15) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Description";
      }
   },
   EVIDENCE(16) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Evidence";
      }
   },
   EXPECTED(17) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Expected";
      }
   },
   LAST_POLICY_UPDATE(18) {

      /**
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {

         return "Last Policy Update";
      }
   };

   private final Integer value;

   private MainframeHeaderKeys(Integer value) {

      this.value = value;
   }

   public Integer getValue() {
      return this.value;
   }

}
