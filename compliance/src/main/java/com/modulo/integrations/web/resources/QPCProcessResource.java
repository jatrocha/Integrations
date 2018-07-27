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

import com.modulo.integrations.commons.persistence.model.QPCEventEntity;
import com.modulo.integrations.commons.qualys.QualysCompilanceProcessor;
import com.modulo.integrations.web.business.MultiThreadedEventProcessor;
import com.modulo.riskmanager.client.AuthenticationService;
import com.modulo.riskmanager.client.to.Auth;

@Component
@Path("/qpc")
public class QPCProcessResource implements ProcessResource {

   @Resource
   private QualysCompilanceProcessor qualysProcessor;

   @Resource
   private AuthenticationService authenticationService;

   @Resource(name = "qualysEventProcessor")
   private MultiThreadedEventProcessor<QPCEventEntity> eventProcessor;

   @Resource
   private EntityManagerFactory factory;

   /** Classe de log. */
   private static final ILog<QPCProcessResource> LOG = Log.get(QPCProcessResource.class);

   @Override
   @GET
   @Path("/load")
   @Produces({ MediaType.APPLICATION_JSON })
   public String load() {

      final List<ErrorDTO> errs = new ArrayList<ErrorDTO>(1);

      try {

         if (Execution.getInstance().isNotExecuting()) {

            Execution.getInstance().start();

            final Auth auth = this.authenticationService.authenticate();

            this.qualysProcessor.with(auth).process();

            Execution.getInstance().stop();

            return LOAD_DONE;
         }

         return LOAD_ALREADY_EXECUTING;

      } catch (final Exception e) {

         e.printStackTrace();

         LOG.error(e, e.getMessage());

         errs.add(new ErrorDTO(e.getMessage()));

         Execution.getInstance().stop();
      }

      throw new EngineRuntimeException(errs);
   }

   @Override
   @GET
   @Path("/process")
   @Produces({ MediaType.APPLICATION_JSON })
   public String process() {

      final List<ErrorDTO> errs = new ArrayList<ErrorDTO>(1);

      try {

         if (Execution.getInstance().isNotExecuting()) {

            Execution.getInstance().start();

            final Auth auth = this.authenticationService.authenticate();

            final List<QPCEventEntity> events = this.list();

            if (!CollectionUtils.isEmpty(events)) {

               this.eventProcessor.with(auth).process(events);

            }

            Execution.getInstance().stop();

            return PROCESS_STARTED;
         }

         return PROCESS_ALREADY_EXECUTING;

      } catch (final Exception e) {

         LOG.error(e, e.getMessage());

         errs.add(new ErrorDTO(e.getMessage()));

         Execution.getInstance().stop();
      }

      throw new EngineRuntimeException(errs);
   }

   @SuppressWarnings("unchecked")
   protected List<QPCEventEntity> list() {

      final EntityManager entityManager = this.factory.createEntityManager();

      return entityManager.createQuery("from QPCEventEntity").getResultList();
   }

}

class Execution {

   private static Execution instance = new Execution();

   private Boolean executing = Boolean.FALSE;

   private Execution() {

      super();
   }

   public void stop() {

      this.executing = Boolean.FALSE;
   }

   public static Execution getInstance() {

      return instance;
   }

   public Boolean isNotExecuting() {

      return !this.executing;
   }

   public void start() {

      this.executing = Boolean.TRUE;
   }

}
