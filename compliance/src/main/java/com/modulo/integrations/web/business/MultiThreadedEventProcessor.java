package com.modulo.integrations.web.business;

import java.util.List;

import com.modulo.integrations.commons.persistence.model.AbstractEventEntity;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;

public abstract class MultiThreadedEventProcessor<T extends AbstractEventEntity> {

   private Auth auth;

   public MultiThreadedEventProcessor<T> with(final Auth auth) {

      this.auth = auth;

      return this;
   }

   protected final Auth getAuth() {

      return this.auth;
   }

   public abstract void process(List<T> events) throws LackOfAuthorizationException;

}
