/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryExecutor;

import com.thesoftwareguild.flightmaster.queryProcessor.Flight;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generates a singleton priority queue that is ordered by unix time. User of
 * this class can add request objects. The queue calls the execute method on the
 * request, which calls the execute method on the query object.
 *
 * @author apprentice
 */
public class ExecutorPQ {

    final private static long FIVE_SECONDS = 5000; // five seconds in ms
    final private ExecutorService queryExecutor = Executors.newSingleThreadExecutor();
    final private PriorityQueue<Request> pq = new PriorityQueue(10, Request.flightQuerySoonest);

    private static ExecutorPQ instance = new ExecutorPQ();

    private final Runnable pqThread = new Runnable() {

        /*
            Makes a request and then checks to see if there are more requests to make.
            If there are, the request object will be added back to the priority queue. The execute 
            method changes the executionTime variable in the request is changed to point to the
            time of the next request if one exists
        */
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

    };

    public static ExecutorPQ getInstance() {
        return instance;
    }

    private ExecutorPQ() {
    }

    /**
     * Adds a request to the PQ. If the request requires multiple queries,
     * multiple items will be added to the queue
     *
     * @param request
     */
    public void addToPQ(Request request) {
     
            pq.add(request);
      
    }

    /**
     * This method starts the single thread PQ. It should be called when the
     * program is initialized.
     */
    public void run() {
        queryExecutor.execute(pqThread);
    }

}
