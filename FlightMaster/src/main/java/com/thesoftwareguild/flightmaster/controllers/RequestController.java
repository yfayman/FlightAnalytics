/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.controllers;

import com.thesoftwareguild.flightmaster.daos.AirportDataDao;
import com.thesoftwareguild.flightmaster.daos.RequestDao;
import com.thesoftwareguild.flightmaster.daos.UserDao;
import com.thesoftwareguild.flightmaster.models.AirportData;
import com.thesoftwareguild.flightmaster.models.RequestParameters;
import com.thesoftwareguild.flightmaster.models.User;
import com.thesoftwareguild.flightmaster.queryExecutor.Request;
import java.util.PriorityQueue;
import javax.validation.Valid;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Handles all traffic that involves query requests
 * @author yan
 */
@RequestMapping(value = "/request")
@Controller
public class RequestController implements ApplicationContextAware {
   
    private RequestDao requestDao;
    private UserDao userDao;
    private AirportDataDao airportDataDao;
    
    //@Autowired
    private ApplicationContext context;
    

    @Autowired
    public RequestController(@Qualifier("requestDaoJdbc")RequestDao requestDao,
            @Qualifier("userDaoJdbc")UserDao userDao,
            @Qualifier("airportDataDaoJdbc")AirportDataDao airportDataDao) {
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.airportDataDao = airportDataDao;
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
            RequestParameters requestWithId = requestDao.add(req);
            Request request = context.getBean(Request.class);
            request.setRequestParameters(requestWithId);
            PriorityQueue<Request> pq = context.getBean("getPQ", PriorityQueue.class);
            pq.add(request);
        }
        
    }
    
    // Sends all IATA codes to the client 
    @RequestMapping(value = "/airportData", method = RequestMethod.GET)
    @ResponseBody
    public AirportData getAirportData(){
        return airportDataDao.getAllAirports();
    }
      
    private User getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.getByUsername(username);
        return user;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
  
}
