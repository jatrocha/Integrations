package com.modulo.integrations.web.business;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.modulo.riskmanager.client.AuthenticationService;
import com.modulo.riskmanager.client.to.Auth;

@Service("authenticationBusiness")
public class AuthenticationBusiness {

   @Resource
   private AuthenticationService authenticationService;

   public Auth authenticate() {
      return null;
   }

}
