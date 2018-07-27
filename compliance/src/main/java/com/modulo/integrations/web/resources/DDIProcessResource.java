package com.modulo.integrations.web.resources;

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

import com.modulo.integrations.commons.ddi.DDIVulnerabilityProcessor;
import com.modulo.integrations.commons.persistence.model.DDIEventEntity;
import com.modulo.integrations.web.business.DDIEventProcessor;
import com.modulo.riskmanager.client.AuthenticationService;
import com.modulo.riskmanager.client.to.Auth;

@Component
@Path("/ddi")
public class DDIProcessResource implements ProcessResource {

   @Resource
   private AuthenticationService authenticationService;

   @Resource
   private DDIVulnerabilityProcessor loadProcessor;

   @Resource
   private EntityManagerFactory factory;

   @Resource
   private DDIEventProcessor eventProcessor;

   /** Classe de log. */
   private static final ILog<DDIProcessResource> LOG = Log.get(DDIProcessResource.class);

   @GET
   @Path("/process")
   @Produces({ MediaType.APPLICATION_JSON })
   @Override
   public String process() {

      final List<ErrorDTO> errs = new ArrayList<ErrorDTO>(1);

      try {

         if (Execution.getInstance().isNotExecuting()) {

            Execution.getInstance().start();

            final Auth auth = this.authenticationService.authenticate();

            final List<DDIEventEntity> events = this.list();

            if (!CollectionUtils.isEmpty(events)) {

               this.eventProcessor.with(auth).process(events);

            }

            Execution.getInstance().stop();

            return "Process done.";
         }

         return "already executing";

      } catch (final Exception e) {

         LOG.error(e, e.getMessage());

         errs.add(new ErrorDTO(e.getMessage()));

         Execution.getInstance().stop();
      }

      throw new EngineRuntimeException(errs);
   }

   @GET
   @Path("/load")
   @Produces({ MediaType.APPLICATION_JSON })
   @Override
   public String load() {

      final List<ErrorDTO> errs = new ArrayList<ErrorDTO>(1);

      try {

         if (Execution.getInstance().isNotExecuting()) {

            Execution.getInstance().start();

            final Auth auth = this.authenticationService.authenticate();

            this.loadProcessor.with(auth).process();

            Execution.getInstance().stop();

            return LOAD_DONE;
         }

         return LOAD_ALREADY_EXECUTING;

      } catch (final Exception e) {

         LOG.error(e, e.getMessage());

         errs.add(new ErrorDTO(e.getMessage()));

         Execution.getInstance().stop();
      }

      throw new EngineRuntimeException(errs);
   }

   @SuppressWarnings("unchecked")
   protected List<DDIEventEntity> list() {

      final EntityManager entityManager = this.factory.createEntityManager();

      return entityManager.createQuery("from DDIEventEntity").getResultList();

   }

}
