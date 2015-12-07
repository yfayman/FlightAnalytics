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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Combines the who(requestParameters) and the how(query) to execute a request. Execution
 * time is set to when the requestor is created. After execute is called, the next
 * execution time is set to current time + the interval as specified by the requestor
 * @author Yan Fayman
 */

@Scope(value = "prototype")
@Component
public class Request {

    @Autowired
    private FlightQuery query;
    
    private RequestParameters requestParameters;
    private long executionTime;

    
//    public Request (RequestParameters requestor) {
////        this.query = query;
//        this.requestParameters = requestor;
//        this.executionTime = System.currentTimeMillis();
//       // this.executionTime = System.currentTimeMillis() + (requestor.getInterval() * 3600000);
//    }
    
    public Request(){
        //this.executionTime = System.currentTimeMillis();
    }

    
    /**
     * Returns the results of the query if the planned execution time is in the past
     * The query is not performed if it's not time yet
     * @return
     * @throws IOException 
     */
    public List<Flight> execute() throws IOException{
        if(executionTime < System.currentTimeMillis()){
            populateQuery();
            List<Flight> execute = query.execute();
            requestParameters.requestMade();
            executionTime = System.currentTimeMillis() + requestParameters.getInterval();
            // Pass information to Dao to store it in persistence layer
            
        return execute;
        }
        else
            return null;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
    
    // order the Requests by their execution time(lowest to highest)
    public static Comparator<Request> flightQuerySoonest = new Comparator<Request>(){

        @Override
        public int compare(Request o1, Request o2) {
            return Long.compare(o1.executionTime, o2.executionTime);
        }
        
    };
    
    public boolean hasRequest(){
        return requestParameters.hasRequest();
    }

    
    private void populateQuery(){
        requestParameters.populateQueryParams(query);
    }

    public int getRequestId(){
        return requestParameters.getRequestId();
    }
    public int getUserId(){
        return requestParameters.getUserId();
    }

    public FlightQuery getQuery() {
        return query;
    }

    public void setQuery(FlightQuery query) {
        this.query = query;
    }

    public void setRequestParameters(RequestParameters requestParameters) {
        this.requestParameters = requestParameters;
    }

 
    
    
}
