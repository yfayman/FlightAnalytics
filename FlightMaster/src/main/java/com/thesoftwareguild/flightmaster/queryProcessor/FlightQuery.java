/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryProcessor;

import com.thesoftwareguild.flightmaster.models.Flight;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yan
 */
public interface FlightQuery {
    public List<Flight> execute() throws IOException;
    
    public void populateData(Map<String, Object> data);
    
}
