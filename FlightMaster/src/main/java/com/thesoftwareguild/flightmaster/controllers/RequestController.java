/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.controllers;

import com.thesoftwareguild.flightmaster.daos.RequestDao;
import com.thesoftwareguild.flightmaster.daos.UserDao;
import com.thesoftwareguild.flightmaster.models.RequestParameters;
import com.thesoftwareguild.flightmaster.models.User;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles all traffic that involves query requests
 * @author yan
 */
@RequestMapping(value = "/request")
@Controller
public class RequestController {
   
    private RequestDao requestDao;
    private UserDao userDao;

    @Autowired
    public RequestController(@Qualifier("requestDaoJdbc")RequestDao requestDao, @Qualifier("userDaoJdbc")UserDao userDao) {
        this.requestDao = requestDao;
        this.userDao = userDao;
    }
    
    /*
        Returns the request page when user clicks on the "Make a Request link"
    */
    @RequestMapping(value = "")
    public String requestIndex(){
        return "request";
    }
    
    /*
        Adds request to database
    */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addRequest(@Valid @RequestBody RequestParameters req){
        
        User user = getLoggedInUser();
        if(user != null){
            req.setUserId(user.getUserId());
            requestDao.add(req);
        }
        
    }
    
    private User getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.getByUsername(username);
        return user;
    }
    
}
