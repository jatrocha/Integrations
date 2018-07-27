package com.modulo.integrations.ddi.exception;

public class ConvertException extends RuntimeException {

   public ConvertException() {
      super();
   }

   public ConvertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

   public ConvertException(String message, Throwable cause) {
      super(message, cause);
   }

   public ConvertException(String message) {
      super(message);
   }

   public ConvertException(Throwable cause) {
      super(cause);
   }

   private static final long serialVersionUID = 8310026690385840698L;

}
