package com.modulo.integrations.web.resources;

public interface ProcessResource {

   static final String LOAD_DONE = "{\"Result\":\"Load done\"}";

   static final String LOAD_ALREADY_EXECUTING = "{\"Result\":\"Load is already executing\"}";

   static final String PROCESS_STARTED = "{\"Result\":\"Process started\"}";

   static final String PROCESS_ALREADY_EXECUTING = "{\"Result\":\"Process is already executing\"}";

   /**
    * Carrega os controles a serem processados e transformados em eventos no Risk Manager.
    *
    * @return ({@link String} contendo o retorno da operacao.
    */
   String load();

   /**
    * Envia os eventos para o Risk Manager.
    *
    * @return ({@link String} contendo o retorno da operacao.
    */
   String process();

}