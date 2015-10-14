/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryProcessor;

import java.util.List;

/**
 *
 * @author yan
 */
public interface FlightQueryResult {
    
    public void setRawResults(Object object);
    
    public List<Flight> getProcessedResults();
    
}
