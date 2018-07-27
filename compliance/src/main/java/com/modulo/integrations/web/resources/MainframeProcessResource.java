package com.modulo.integrations.web.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import br.com.cygnus.framework.dto.ErrorDTO;
import br.com.cygnus.framework.exception.EngineRuntimeException;
import br.com.cygnus.framework.log.Log;
import br.com.cygnus.framework.template.log.ILog;

import com.modulo.integrations.commons.mainframe.MainframeCompilanceProcessor;
import com.modulo.integrations.commons.persistence.model.MainframeEventEntity;
import com.modulo.integrations.web.business.MainframeEventProcessor;
import com.modulo.riskmanager.client.AuthenticationService;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;

@Component
@Path("/mainframe")
public class MainframeProcessResource {

   @Resource
   private MainframeCompilanceProcessor mainframeProcessor;

   @Resource
   private AuthenticationService authenticationService;

   @Resource
   private MainframeEventProcessor eventProcessor;

   @Resource
   private EntityManagerFactory factory;

   /** Classe de log. */
   private static final ILog<MainframeProcessResource> LOG = Log.get(MainframeProcessResource.class);

   @GET
   @Path("/process")
   @Produces({ MediaType.APPLICATION_JSON })
   public String process() {
      final List<ErrorDTO> errs = new ArrayList<ErrorDTO>(1);

      try {

         final Auth auth = this.authenticationService.authenticate();

         final List<MainframeEventEntity> events = this.list();

         if (!CollectionUtils.isEmpty(events)) {

            this.eventProcessor.with(auth).process(events);
         }

         this.eventProcessor.with(auth).process(this.mainframeProcessor.with(auth).process());

         return "Done.";

      } catch (final LackOfAuthorizationException e) {

         LOG.error(e, e.getMessage());

         errs.add(new ErrorDTO(e.getMessage()));

      } catch (final IOException e) {

         LOG.error(e, e.getMessage());

         errs.add(new ErrorDTO(e.getMessage()));
      }

      throw new EngineRuntimeException(errs);
   }

   @SuppressWarnings("unchecked")
   protected List<MainframeEventEntity> list() {

      final EntityManager entityManager = this.factory.createEntityManager();

      return entityManager.createQuery("from MainframeEventEntity").getResultList();

   }
}
