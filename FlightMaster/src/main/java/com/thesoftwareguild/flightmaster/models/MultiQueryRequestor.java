/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import java.util.Date;

/**
 *
 * @author apprentice
 */
public class MultiQueryRequestor implements Requestor{
    //Analysis is inheritely price/person
    
    private int requestId;
    private String requestor;
    private String origin;
    private String destination;
    private Date depDate;
    private Date retDate;
    private long interval; // interval of queries in ms
    private int numberQueries;

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

   
    
    
}
