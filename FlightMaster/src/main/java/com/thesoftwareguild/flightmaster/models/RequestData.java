/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import com.thesoftwareguild.flightmaster.queryProcessor.FlightQuery;
import java.util.Date;

/**
 * Abstract class that must be implemented by all requestors. Has fields for all
 * the basic information required for a flight query.
 * @author Yan
 */
public class RequestData {
   
    
    public static final long SECOND = 1000;
    public static final long MINUTE = SECOND* 60;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;
    public static final long WEEK = DAY * 7;
    
    private long interval; // interval of queries in ms
    private int numberQueries;
    private int requestId;
    private int userId;
    private int maxStops;
    private String origin;
    private String destination;
    private Date depDate;
    private Date retDate;
    private int adultPassengers;
    private int childPassengers;
    private int seniorPassengers;
    private int infantPassengers;
    

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(Date depDate) {
        this.depDate = depDate;
    }

    public Date getRetDate() {
        return retDate;
    }

    public void setRetDate(Date retDate) {
        this.retDate = retDate;
    }

    public int getAdultPassengers() {
        return adultPassengers;
    }

    public void setAdultPassengers(int adultPassengers) {
        this.adultPassengers = adultPassengers;
    }

    public int getChildPassengers() {
        return childPassengers;
    }

    public void setChildPassengers(int childPassengers) {
        this.childPassengers = childPassengers;
    }

    public int getSeniorPassengers() {
        return seniorPassengers;
    }

    public void setSeniorPassengers(int seniorPassengers) {
        this.seniorPassengers = seniorPassengers;
    }

    public int getInfantPassengers() {
        return infantPassengers;
    }

    public void setInfantPassengers(int infantPassengers) {
        this.infantPassengers = infantPassengers;
    }

    public int getMaxStops() {
        return maxStops;
    }

    public void setMaxStops(int maxStops) {
        this.maxStops = maxStops;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumberQueries() {
        return numberQueries;
    }

    public void setNumberQueries(int numberQueries) {
        this.numberQueries = numberQueries;
    }

    
    
    

   


    
    // There are requests to be made
   
    public boolean hasRequest() {
        return numberQueries > 0;
    }
    
    // to be called after a request is complete
     public void requestMade() {
        numberQueries--;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
    
     
 
    public long getInterval() {
        return interval;
    }
    
    /**
     * populates a query object with information from the requestor. The query
     * object must be populated before it's execute method can return a result
     * @param query 
     */
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
