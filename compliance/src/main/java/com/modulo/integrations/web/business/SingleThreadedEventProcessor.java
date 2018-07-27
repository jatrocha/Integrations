package com.modulo.integrations.web.business;

import java.util.List;

import com.modulo.integrations.commons.persistence.model.AbstractEventEntity;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;

public abstract class SingleThreadedEventProcessor<T extends AbstractEventEntity> {

   private Auth auth;

   public SingleThreadedEventProcessor<T> with(final Auth auth) {

      this.auth = auth;

      return this;
   }

   public abstract void process(List<T> events) throws LackOfAuthorizationException;

   @Deprecated
   protected abstract void deleteEvent(T eventEntity);

   protected abstract String getEventType();

   protected final Auth getAuth() {

      return this.auth;
   }
}
