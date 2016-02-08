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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.log4j.Logger;
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

    private static Logger logger = Logger.getLogger(ExecutorPQ.class);
    private ApplicationContext context;
    
    final private ScheduledExecutorService queryExecutor = Executors.newScheduledThreadPool(1);
    
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
        queryExecutor.schedule(pqThread, 1, TimeUnit.SECONDS);
        
        RequestDao requestDao = context.getBean("requestDaoJdbc", RequestDao.class);
         List<RequestParameters> liveRequests = requestDao.getLiveRequests();
        for (RequestParameters liveRequest : liveRequests) {
            Request request = context.getBean(Request.class);
            request.setRequestParameters(liveRequest);
            request.setExecutionTime(liveRequest.getNextQueryTime());
            addToExecutor(request);
        }
        logger.info("PQ Thread started and live requests loaded");
    }
    
    @PreDestroy
    public void shutdown(){
        try {
            if(!queryExecutor.awaitTermination(5, TimeUnit.SECONDS))
                queryExecutor.shutdownNow();
        } catch (InterruptedException ex) {
            logger.error("Error during shutdown: " + ex);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
