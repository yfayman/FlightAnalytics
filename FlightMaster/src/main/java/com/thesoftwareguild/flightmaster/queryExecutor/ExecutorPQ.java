/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryExecutor;

import com.thesoftwareguild.flightmaster.queryProcessor.FlightQueryResult;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class ExecutorPQ {
    
    final private static long ONE_MINUTE = 60000; // a minute in ms
    final private ExecutorService queryExecutor = Executors.newSingleThreadExecutor();
    final private PriorityQueue<Request> pq = new PriorityQueue(10,Request.flightQuerySoonest );
    
    private static ExecutorPQ instance = new ExecutorPQ();
    
    private final Runnable pqThread = new Runnable(){

        @Override
        public void run() {
            while(true){
                try {
                    if(pq.peek()!= null && pq.peek().getNextExecutionTime() < System.currentTimeMillis()){
                        try {
                            Request request = pq.poll();
                            FlightQueryResult result = request.execute();
                            
                            // add code here to deal with storing the result in the database
                            
                            pq.add(new Request(request.getQuery(),request.getRequestor()));
                        } catch (IOException ex) {
                            Logger.getLogger(ExecutorPQ.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    this.wait(ONE_MINUTE);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ExecutorPQ.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    };
    
    public static ExecutorPQ getInstance(){
        return instance;
    }
    
    private ExecutorPQ(){  
    }
    
    public void addToPQ(Request request){
        pq.add(request);
    }
    
    private void run(){
        queryExecutor.execute(pqThread);
    }
    
}
