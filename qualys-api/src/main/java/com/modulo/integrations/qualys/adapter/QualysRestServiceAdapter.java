package com.modulo.integrations.qualys.adapter;

import java.util.ArrayList;
import java.util.List;

import br.com.cygnus.framework.dto.ErrorDTO;
import br.com.cygnus.framework.exception.EngineRuntimeException;
import br.com.cygnus.framework.service.RESTServiceAdapter;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class QualysRestServiceAdapter extends RESTServiceAdapter {

   @SuppressWarnings("unchecked")
   @Override
   protected <T extends EngineRuntimeException> T createRuntimeException(ClientResponse response) {

      final List<ErrorDTO> errors = new ArrayList<ErrorDTO>();

      errors.add(response.getEntity(new ErrorGenericType()));

      return (T) new EngineRuntimeException(errors);
   }

   private static class ErrorGenericType extends GenericType<ErrorDTO> {
   }

}
