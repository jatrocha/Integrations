package com.modulo.integrations.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/imperva")
public class ImpervaProcessResource {

   @GET
   @Path("/process")
   @Produces({ MediaType.APPLICATION_JSON })
   public String process() {

      return null;
   }

}
