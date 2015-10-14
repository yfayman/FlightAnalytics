/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryProcessor;

import com.google.api.services.qpxExpress.model.TripsSearchResponse;
import java.util.List;

/**
 *
 * @author yan
 */
class QPXFlightQueryResult implements FlightQueryResult{

    private TripsSearchResponse result;
    
    
    
    @Override
    public void setRawResults(Object object) {
        this.result = (TripsSearchResponse) object;
    }

    @Override
    public List<Flight> getProcessedResults() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
