/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryExecutor;

import com.thesoftwareguild.flightmaster.models.RequestParameters;
import com.thesoftwareguild.flightmaster.models.Flight;
import com.thesoftwareguild.flightmaster.queryProcessor.FlightQuery;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Combines the who(requestor) and the how(query) to execute a request. Execution
 * time is set to when the requestor is created. After execute is called, the next
 * execution time is set to current time + the interval as specified by the requestor
 * @author Yan Fayman
 */
public  class Request {

    private FlightQuery query;
    private RequestParameters requestData;
    private long executionTime;
    
    public Request(FlightQuery query, RequestParameters requestor) {
        this.query = query;
        this.requestData = requestor;
        this.executionTime = System.currentTimeMillis();
       // this.executionTime = System.currentTimeMillis() + (requestor.getInterval() * 3600000);
    }

    
    /**
     * Returns the results of the query if the planned execution time is in the past
     * The query is not performed if it's not time yet
     * @return
     * @throws IOException 
     */
    public List<Flight> execute() throws IOException{
        if(executionTime < System.currentTimeMillis()){
            List<Flight> execute = query.execute();
            requestData.requestMade();
            executionTime = System.currentTimeMillis() + requestData.getInterval();
            // Pass information to Dao to store it in persistence layer
            
        return execute;
        }
        else
            return null;
    }

    public long getExecutionTime() {
        return executionTime;
    }
    
    // order the Requests by their execution time(lowest to highest)
    public static Comparator<Request> flightQuerySoonest = new Comparator<Request>(){

        @Override
        public int compare(Request o1, Request o2) {
            return Long.compare(o1.executionTime, o2.executionTime);
        }
        
    };
    
    public boolean hasRequest(){
        return requestData.hasRequest();
    }

    
    private void populateQuery(){
        requestData.populateQueryParams(query);
    }
    
    

}
