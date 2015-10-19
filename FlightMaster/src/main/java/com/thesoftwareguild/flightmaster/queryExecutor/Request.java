/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryExecutor;

import com.thesoftwareguild.flightmaster.models.Requestor;
import com.thesoftwareguild.flightmaster.queryProcessor.Flight;
import com.thesoftwareguild.flightmaster.queryProcessor.FlightQuery;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author apprentice
 */
public  class Request {

    private FlightQuery query;
    private Requestor requestor;
    private long nextExecutionTime;
    
    public Request(FlightQuery query, Requestor requestor) {
        this.query = query;
        this.requestor = requestor;
        this.nextExecutionTime = System.currentTimeMillis() + (requestor.getInterval() * 3600000);
    }

    
    /**
     * Returns the results of the query if the planned execution time is in the past
     * The query is not performed if it's not time yet
     * @return
     * @throws IOException 
     */
    public List<Flight> execute() throws IOException{
        if(nextExecutionTime < System.currentTimeMillis()){
            List<Flight> execute = query.execute();
            requestor.requestMade();
        return execute;
        }
        else
            return null;
    }

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }
    
    public static Comparator<Request> flightQuerySoonest = new Comparator<Request>(){

        @Override
        public int compare(Request o1, Request o2) {
            return Long.compare(o1.nextExecutionTime, o2.nextExecutionTime);
        }
        
    };

    public FlightQuery getQuery() {
        return query;
    }

    public Requestor getRequestor() {
        return requestor;
    }
    
    
    
    

}
