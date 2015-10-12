/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryExecutor;

import com.thesoftwareguild.flightmaster.models.AnalysisRequest;
import com.thesoftwareguild.flightmaster.queryProcessor.FlightQuery;
import com.thesoftwareguild.flightmaster.queryProcessor.FlightQueryResult;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author apprentice
 */
public  class FlightQueryExecutable {

    private FlightQuery query;
    private AnalysisRequest analysisRequest;
    private long nextExecutionTime;
    
    public FlightQueryExecutable(FlightQuery query, AnalysisRequest analysisRequest) {
        this.query = query;
        this.analysisRequest = analysisRequest;
        this.nextExecutionTime = System.currentTimeMillis() + (analysisRequest.getInterval() * 3600000);
    }

    
    /**
     * Returns the results of the query if the planned execution time is in the past
     * The query is not performed if it's not time yet
     * @return
     * @throws IOException 
     */
    public List<FlightQueryResult> execute() throws IOException{
        if(nextExecutionTime < System.currentTimeMillis()){
            List<FlightQueryResult> execute = query.execute();
        return execute;
        }
        else
            return null;
    }

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }
    
    public static Comparator<FlightQueryExecutable> flightQuerySoonest = new Comparator<FlightQueryExecutable>(){

        @Override
        public int compare(FlightQueryExecutable o1, FlightQueryExecutable o2) {
            return Long.compare(o1.nextExecutionTime, o2.nextExecutionTime);
        }
        
    };

    public FlightQuery getQuery() {
        return query;
    }

    public AnalysisRequest getAnalysisRequest() {
        return analysisRequest;
    }
    
    
    
    

}
