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
 * @author yan
 */
public abstract class Requestor {
    
    protected int requestId;
    protected int maxStops;
    protected String requestor;
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

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
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

    
    

   


    
    // There are requests to be made
    abstract public boolean hasRequest();
    
    // to be called after a request is complete
    abstract public void requestMade();
    
    abstract public long getInterval();
    
    abstract public void populateQueryParams(FlightQuery query);
    
}
