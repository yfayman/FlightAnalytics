/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.debugging;


import org.jboss.logging.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author yan
 */
@Component
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {

   private static Logger logger = Logger.getLogger(AuthenticationEventListener.class);

   @Override
   public void onApplicationEvent(AbstractAuthenticationEvent authenticationEvent) {
      if (authenticationEvent instanceof InteractiveAuthenticationSuccessEvent) {
         // ignores to prevent duplicate logging with AuthenticationSuccessEvent
         return;
      }
      Authentication authentication = authenticationEvent.getAuthentication();
      String auditMessage = "Login attempt with username: " + authentication.getName() + "\t\tSuccess: " + authentication.isAuthenticated();
      logger.info(auditMessage);
   }

}