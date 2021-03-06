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
import com.thesoftwareguild.flightmaster.models.Flight;
import com.thesoftwareguild.flightmaster.models.RequestParameters;
import com.thesoftwareguild.flightmaster.models.User;
import com.thesoftwareguild.flightmaster.queryExecutor.Executor;
import com.thesoftwareguild.flightmaster.queryExecutor.ExecutorPQ;
import com.thesoftwareguild.flightmaster.queryExecutor.Request;
import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles all traffic that involves query requests
 *
 * @author yan
 */
@RequestMapping(value = "/request")
@Controller
public class RequestController implements ApplicationContextAware {
    
    final static Logger logger = Logger.getLogger(RequestController.class);

    private RequestDao requestDao;
    private UserDao userDao;
    private AirportDataDao airportDataDao;

    //@Autowired
    private ApplicationContext context;

    @Autowired
    public RequestController(@Qualifier("requestDaoJdbc") RequestDao requestDao,
            @Qualifier("userDaoJdbc") UserDao userDao,
            @Qualifier("airportDataDaoJdbc") AirportDataDao airportDataDao) {
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.airportDataDao = airportDataDao;
    }

    /*
     Returns the request page when user clicks on the "Make a Request link"
     */
    @RequestMapping(value = "")
    public String requestIndex() {
        return "request";
    }

    /*
     Adds request to database
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addRequest(@Valid @RequestBody RequestParameters req) {

        User user = getLoggedInUser();
        if (user != null) {
            req.setUserId(user.getUserId());
            RequestParameters requestWithId = requestDao.add(req);
            Request request = context.getBean(Request.class);
            request.setRequestParameters(requestWithId);
            request.setExecutionTime(System.currentTimeMillis());
            Executor executor = context.getBean(ExecutorPQ.class);
            executor.addToExecutor(request);
        }

    }

    // Sends all IATA codes to the client 
    @RequestMapping(value = "/airportData", method = RequestMethod.GET)
    @ResponseBody
    public AirportData getAirportData() {
        return airportDataDao.getAllAirports();
    }

    private User getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username.equals("anonymousUser")) {
            return null;
        }
        User user = userDao.getByUsername(username);
        return user;
    }

    @RequestMapping(value = "/currentrequests", method = RequestMethod.GET)
    public String viewRequests(Model model) {
        List<RequestParameters> requests;
        User user = getLoggedInUser();
        if (user != null) {
            requests = requestDao.getRequestsByUserId(user.getUserId());
        } else {
            requests = null;
        }

        model.addAttribute("requests", requests);
        return "viewrequests";
    }

    @RequestMapping(value = "/currentrequest/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Flight> getRequestData(@PathVariable("id") int id) {
        return requestDao.getDataByRequestId(id);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
