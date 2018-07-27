package com.modulo.integrations.mainframe.exception;

public class InvalidFileRuntimeException extends RuntimeException {

   private static final long serialVersionUID = -5741319557360668326L;

   public InvalidFileRuntimeException() {
      super();
   }

   public InvalidFileRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

   public InvalidFileRuntimeException(String message, Throwable cause) {
      super(message, cause);
   }

   public InvalidFileRuntimeException(String message) {
      super(message);
   }

   public InvalidFileRuntimeException(Throwable cause) {
      super(cause);
   }

}
