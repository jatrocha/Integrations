package com.modulo.integrations.qualys.exception;

import br.com.cygnus.framework.template.business.exception.AbstractBusinessException;

public class QualysBusinessException extends AbstractBusinessException {

   private static final long serialVersionUID = -355454585108787553L;

   public QualysBusinessException(String message, String chave, Throwable cause) {
      super(message, chave, cause);
   }

   public QualysBusinessException(String message, String chave) {
      super(message, chave);
   }

   public QualysBusinessException(String message, Throwable cause) {
      super(message, cause);
   }

   public QualysBusinessException(Throwable cause) {
      super(cause);
   }

}
