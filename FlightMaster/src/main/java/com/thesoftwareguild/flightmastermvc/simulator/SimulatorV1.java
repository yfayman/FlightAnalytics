/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmastermvc.simulator;

import com.thesoftwareguild.flightmaster.models.MultiQueryRequestor;
import com.thesoftwareguild.flightmaster.models.Requestor;
import com.thesoftwareguild.flightmaster.queryExecutor.ExecutorPQ;
import com.thesoftwareguild.flightmaster.queryProcessor.MockFlightQuery;
import java.util.Date;

/**
 *
 * @author yan
 */
public class SimulatorV1 {
    ExecutorPQ epq;
    MockFlightQuery mfq;
    Requestor mqr;
    
    public SimulatorV1(){
        epq = ExecutorPQ.getInstance();
        mfq = new MockFlightQuery();
        mqr = new MultiQueryRequestor();
        mqr.setAdultPassengers(1);
        mqr.setDepDate(new Date(System.currentTimeMillis()+(1000*60*60*24*7))); // departure date is one week from today
        mqr.setRetDate(new Date(System.currentTimeMillis()+(1000*60*60*24*7)));
    }
}
