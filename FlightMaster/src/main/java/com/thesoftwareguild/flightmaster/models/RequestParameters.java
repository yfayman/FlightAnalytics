/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import com.thesoftwareguild.flightmaster.queryProcessor.FlightQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  Has fields for all the basic information required for a flight query.
 * @author Yan
 */
public class RequestParameters {
       
    private long interval; // interval of queries in milliseconds
    private long nextQueryTime;
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

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getNextQueryTime() {
        return nextQueryTime;
    }

    public void setNextQueryTime(long nextQueryTime) {
        this.nextQueryTime = nextQueryTime;
    }

    

    
    /**
     * populates a query object with information from this object. The query
     * object must be populated before it's execute method can return a result
     * @param query 
     */
    public void populateQueryParams(FlightQuery query) {
    	Map<String, Object> data = new HashMap<>();
    	data.put("adultPassengers", this.adultPassengers);
    	data.put("childPassengers", this.childPassengers);
    	data.put("depDate", this.depDate);
    	data.put("destination", this.destination);
    	data.put("infantPassengers", this.infantPassengers);
    	data.put("maxStops", this.maxStops);
    	data.put("origin", this.origin);
    	data.put("retDate", this.retDate);
    	data.put("seniorPassengers", this.seniorPassengers);
    	data.put("requestId", this.requestId);


        query.populateData(data);
    }
    
}
