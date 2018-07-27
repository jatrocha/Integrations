package com.modulo.integrations.qualys.exception;

public class QualysServiceException extends Exception {

   public QualysServiceException() {
      super();
   }

   public QualysServiceException(String message, Throwable cause) {
      super(message, cause);
   }

   public QualysServiceException(String message) {
      super(message);
   }

   public QualysServiceException(Throwable cause) {
      super(cause);
   }

   private static final long serialVersionUID = 1931660476401572744L;

}
