/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import com.thesoftwareguild.flightmaster.queryProcessor.FlightQuery;
import java.util.Date;

/**
 *
 * @author apprentice
 */
public class MultiQueryRequestor extends Requestor{
    //Analysis is inheritely price/person
    
    private long interval; // interval of queries in ms
    private int numberQueries;

   
    @Override
    public long getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public boolean hasRequest() {
        return numberQueries > 0;
    }

    @Override
    public void requestMade() {
        numberQueries--;
    }

    @Override
    public void populateQueryParams(FlightQuery query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
}
