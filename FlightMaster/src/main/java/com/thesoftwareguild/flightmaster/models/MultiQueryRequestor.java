/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import com.thesoftwareguild.flightmaster.queryProcessor.FlightQuery;
import java.util.Date;

/**
 * This subclass of requestor is for handling multiple queries at a certain interval.
 * If the interval is 6 hours, the query should be happening about 4 times a day.
 * @author apprentice
 */
public class MultiQueryRequestor extends Requestor{
    //Analysis is inheritely price/person
    
    public static final long SECOND = 1000;
    public static final long MINUTE = SECOND* 60;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;
    public static final long WEEK = DAY * 7;
    
    private long interval; // interval of queries in ms
    private int numberQueries;

    public MultiQueryRequestor(int numberQueries, long interval){
        this.numberQueries = numberQueries;
        this.interval = interval;
    }
   
    @Override
    public long getInterval() {
        return interval;
    }

//    public void setInterval(long interval) {
//        this.interval = interval;
//    }

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
        query.setAdultPassengers(this.adultPassengers);
        query.setChildPassengers(this.childPassengers);
        query.setDepartDate(this.depDate);
        query.setDestination(this.destination);
        query.setInfantInSeatCount(this.infantPassengers);
        query.setMaxStops(this.maxStops);
        query.setOrigin(this.origin);
        query.setReturnDate(this.retDate);
        query.setSeniorPassengers(this.seniorPassengers);
    }

   
    
    
}
