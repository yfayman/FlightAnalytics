/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.simulator;

import com.thesoftwareguild.flightmaster.models.MultiQueryRequestor;
import com.thesoftwareguild.flightmaster.models.Requestor;
import com.thesoftwareguild.flightmaster.queryExecutor.ExecutorPQ;
import com.thesoftwareguild.flightmaster.queryExecutor.Request;
import com.thesoftwareguild.flightmaster.queryProcessor.MockFlightQuery;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class runs the simulation using the MockFlightQuery. This should be run
 * independent of servlet container.
 *
 * @author yan
 */
public class SimulatorV1 {

    ExecutorPQ pq;
    MockFlightQuery mockFlightQuery, mockFlightQuery2, mockFlightQuery3;
    MultiQueryRequestor multiQueryRequestor, multiQueryRequestor2, multiQueryRequestor3;

    public SimulatorV1() {
        pq = ExecutorPQ.getInstance();

        mockFlightQuery = new MockFlightQuery();
        multiQueryRequestor = new MultiQueryRequestor(3, MultiQueryRequestor.MINUTE);
        multiQueryRequestor.setAdultPassengers(1);
        multiQueryRequestor.setMaxStops(1);
        multiQueryRequestor.setDepDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7))); // departure date is one week from today
        multiQueryRequestor.setRetDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 14))); // return is 2 weeks from today
        multiQueryRequestor.setOrigin("NYC");
        multiQueryRequestor.setDestination("ORD");
        multiQueryRequestor.populateQueryParams(mockFlightQuery);
        Request request1 = new Request(mockFlightQuery, multiQueryRequestor);
        pq.addToPQ(request1);
        
        
        mockFlightQuery2 = new MockFlightQuery();
        multiQueryRequestor2 = new MultiQueryRequestor(10, MultiQueryRequestor.MINUTE*2);
        multiQueryRequestor2.setAdultPassengers(1);
        multiQueryRequestor2.setMaxStops(1);
        multiQueryRequestor2.setDepDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 8))); // departure date 8 days from today
        multiQueryRequestor2.setRetDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 15))); // return is 2 weeks +1 day from today
        multiQueryRequestor2.setOrigin("ORD");
        multiQueryRequestor2.setDestination("NYC");
        multiQueryRequestor2.populateQueryParams(mockFlightQuery2);
        Request request2 = new Request(mockFlightQuery2, multiQueryRequestor2);
        pq.addToPQ(request2);
        
        
        mockFlightQuery3 = new MockFlightQuery();
        multiQueryRequestor3 = new MultiQueryRequestor(10, MultiQueryRequestor.MINUTE*3);
        multiQueryRequestor3.setAdultPassengers(1);
        multiQueryRequestor3.setMaxStops(1);
        multiQueryRequestor3.setDepDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 6))); // departure date is one week from today
        multiQueryRequestor3.setRetDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 14))); // return is 2 weeks from today
        multiQueryRequestor3.setOrigin("LAX");
        multiQueryRequestor3.setDestination("NYC");
        multiQueryRequestor3.populateQueryParams(mockFlightQuery3);
        Request request3 = new Request(mockFlightQuery3, multiQueryRequestor3);
        pq.addToPQ(request3);

    }

    public static void main(String[] args) {
        SimulatorV1 sim = new SimulatorV1();
        sim.pq.run();
        
        

    }
}
