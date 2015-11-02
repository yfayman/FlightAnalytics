/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import com.thesoftwareguild.flightmaster.queryProcessor.FlightQuery;

/**
 * Implementation for a single query request
 * @author Yan
 */
public class SingleQueryRequestor extends Requestor {
    
    public boolean wasExecuted = false;
    @Override
    public boolean hasRequest() {
        return !wasExecuted;
    }

    @Override
    public void requestMade() {
        wasExecuted = true;
    }

    @Override
    public long getInterval() {
        return 0;
    }

    @Override
    public void populateQueryParams(FlightQuery query) {
        query.setAdultPassengers(this.adultPassengers);
        query.setChildPassengers(this.childPassengers);
        query.setSeniorPassengers(this.seniorPassengers);
        query.setInfantInSeatCount(this.infantPassengers);
        query.setDepartDate(this.depDate);
        query.setReturnDate(this.retDate);
        query.setOrigin(this.origin);
        query.setDestination(this.destination);
        query.setMaxStops(this.maxStops);
    }
    
}
