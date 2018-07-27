package com.modulo.integrations.web.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Cria filtro que possibilite que os endpoints sejam acessados por IPs diferentes utilizando o JQuery.
 *
 * @author joao.rocha
 *
 */
public class ResourceFilter implements Filter {

   private static final Logger LOG = Logger.getLogger(ResourceFilter.class.getName());

   /**
    * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
    */
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {

   }

   /**
    * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
    */
   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

      LOG.log(Level.INFO, "JQuery Ajax Filter");

      final HttpServletResponse res = (HttpServletResponse) response;

      res.addHeader("Access-Control-Allow-Origin", "*");

      res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");

      res.addHeader("Access-Control-Allow-Headers", "Content-Type");

      chain.doFilter(request, response);

   }

   /**
    * @see javax.servlet.Filter#destroy()
    */
   @Override
   public void destroy() {

   }

}
