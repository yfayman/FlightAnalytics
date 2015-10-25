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
 * @author yan
 */
public abstract class Requestor {
    
    protected int requestId;
    protected int maxStops;
    protected String origin;
    protected String destination;
    protected Date depDate;
    protected Date retDate;
    protected int adultPassengers;
    protected int childPassengers;
    protected int seniorPassengers;
    protected int infantPassengers;

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

    
    

   


    
    // There are requests to be made
    abstract public boolean hasRequest();
    
    // to be called after a request is complete
    abstract public void requestMade();
    
    abstract public long getInterval();
    
    /**
     * populates a query object with information from the requestor. The query
     * object must be populated before it's execute method can return a result
     * @param query 
     */
    abstract public void populateQueryParams(FlightQuery query);
    
}
