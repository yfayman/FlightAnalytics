/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryExecutor;

import com.thesoftwareguild.flightmaster.daos.RequestDao;
import com.thesoftwareguild.flightmaster.models.RequestParameters;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Generates a singleton priority queue that is ordered by unix time. User of
 * this class can add request objects. The queue calls the execute method on the
 * request, which calls the execute method on the query object.
 *
 * @author Yan
 */
@Component
public class ExecutorPQ implements Executor {

    
    private ApplicationContext context;
    
    final private ExecutorService queryExecutor = Executors.newSingleThreadExecutor();
    
    @Autowired
    private PriorityQueue<Request> pq;// = new PriorityQueue(10, Request.flightQuerySoonest);
    
    @Autowired
    private Runnable pqThread;

    public PriorityQueue<Request> getPq() {
        return pq;
    }

    public void setPq(PriorityQueue<Request> pq) {
        this.pq = pq;
    }

    public Runnable getPqThread() {
        return pqThread;
    }

    public void setPqThread(Runnable pqThread) {
        this.pqThread = pqThread;
    }
    
    
    
    /*
        Eager singleton implementation of the priority queue. I used this approach
        to remove risk of executing queries twice
    */
     
    //private static ExecutorPQ instance = new ExecutorPQ();

//    private final Runnable pqThread = new Runnable() {
//
//        /*
//            Makes a request and then checks to see if there are more requests to make.
//            If there are, the request object will be added back to the priority queue. The execute 
//            method changes the executionTime variable in the request is changed to point to the
//            time of the next request if one exists
//        */
//        @Override
//        public void run() {
//            while (true) {
//                try {
//                    if (pq.peek() != null && pq.peek().getExecutionTime() < System.currentTimeMillis()) {
//                        try {
//                            Request request = pq.poll();
//                            List<Flight> result = request.execute();
//                            if(request.hasRequest())
//                                pq.add(request);
//                            else 
//                                System.out.println("Requests depleted");
//                            // add code here to deal with storing the result in the database
//                           
//                        } catch (IOException ex) {
//                            Logger.getLogger(ExecutorPQ.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                    
//                    Thread.sleep(FIVE_SECONDS);
//                    
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(ExecutorPQ.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }

 //   };

    

    /**
     * Adds a request to the PQ. If the request requires multiple queries,
     * multiple items will be added to the queue
     *
     * @param request
     */
    @Override
    public void addToExecutor(Request request) {
     
            pq.add(request);
      
    }

    /**
     * This method starts the single thread PQ. It should be called when the
     * program is initialized.
     */
    @PostConstruct
    @Override
    public void run() {
        System.out.println("Starting PQ thread");
        queryExecutor.execute(pqThread);
        
        RequestDao requestDao = context.getBean("requestDaoJdbc", RequestDao.class);
         List<RequestParameters> liveRequests = requestDao.getLiveRequests();
        for (RequestParameters liveRequest : liveRequests) {
            Request request = context.getBean(Request.class);
            request.setRequestParameters(liveRequest);
            request.setExecutionTime(liveRequest.getNextQueryTime());
            addToExecutor(request);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
    
    

}
