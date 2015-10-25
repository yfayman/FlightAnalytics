/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmastermvc.simulator;

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
    MockFlightQuery mockFlightQuery;
    MultiQueryRequestor multiQueryRequestor;

    public SimulatorV1() {
        pq = ExecutorPQ.getInstance();

        mockFlightQuery = new MockFlightQuery();
        multiQueryRequestor = new MultiQueryRequestor(10, MultiQueryRequestor.MINUTE);
        multiQueryRequestor.setAdultPassengers(1);
        multiQueryRequestor.setMaxStops(1);
        multiQueryRequestor.setDepDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7))); // departure date is one week from today
        multiQueryRequestor.setRetDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 14))); // return is 2 weeks from today
        multiQueryRequestor.setOrigin("NYC");
        multiQueryRequestor.setDestination("ORD");
        multiQueryRequestor.populateQueryParams(mockFlightQuery);
        Request request1 = new Request(mockFlightQuery, multiQueryRequestor);
        pq.addToPQ(request1);

    }

    public static void main(String[] args) {
        SimulatorV1 sim = new SimulatorV1();
        sim.pq.run();
        
        while(true){
            System.out.println("On the main thread(or whatever this is");
            try {
                Thread.sleep(1000*10);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimulatorV1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
