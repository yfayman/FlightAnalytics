/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryExecutor;

import com.thesoftwareguild.flightmaster.models.Flight;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author yan
 */
@Component
public class PQThread implements Runnable{
    
    
    public PQThread(){
    }
    
    @Autowired
    private  PriorityQueue<Request> pq;
    final private static long FIVE_SECONDS = 5000; // five seconds in ms

    @Override
    public void run() {
        while (true) {
                try {
                    if (pq.peek() != null && pq.peek().getExecutionTime() < System.currentTimeMillis()) {
                        try {
                            Request request = pq.poll();
                            List<Flight> result = request.execute();
                            if(request.hasRequest())
                                pq.add(request);
                            else 
                                System.out.println("Requests depleted");
                            // add code here to deal with storing the result in the database
                           
                        } catch (IOException ex) {
                            Logger.getLogger(ExecutorPQ.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    Thread.sleep(FIVE_SECONDS);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(ExecutorPQ.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    public PriorityQueue<Request> getPq() {
        return pq;
    }

    public void setPq(PriorityQueue<Request> pq) {
        this.pq = pq;
    }
    
   
    
}
