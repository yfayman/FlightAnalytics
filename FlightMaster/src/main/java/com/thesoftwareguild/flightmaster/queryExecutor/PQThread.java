/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryExecutor;

import com.thesoftwareguild.flightmaster.daos.RequestDao;
import com.thesoftwareguild.flightmaster.models.Flight;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author yan
 */
@Component
public class PQThread implements Runnable{
    @Qualifier("requestDaoJdbc")
    @Autowired
    private RequestDao requestDao;
    
    private boolean shutdown;
    
    public PQThread(){
    }
    
    @Autowired
    private  PriorityQueue<Request> pq;

    @Override
    public void run() {
        while (!shutdown) {      
            if (pq.peek() != null && pq.peek().getExecutionTime() < System.currentTimeMillis()) {
                try {
                    Request request = pq.poll();
                    List<Flight> result = request.execute();
                    requestDao.addFlights(request.getRequestId(), result);
                    if(request.hasRequest())
                        pq.add(request);
                    else 
                        System.out.println("Requests depleted");

                } catch (IOException ex) {
                    Logger.getLogger(ExecutorPQ.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @PostConstruct
    public void init(){
        this.shutdown = false;
    }
    
    @PreDestroy
    public void shutdown(){
        this.shutdown = true;
    }

    public PriorityQueue<Request> getPq() {
        return pq;
    }

    public void setPq(PriorityQueue<Request> pq) {
        this.pq = pq;
    }

    public void setRequestDao(RequestDao requestDao) {
        this.requestDao = requestDao;
    }
    
   
    
}
