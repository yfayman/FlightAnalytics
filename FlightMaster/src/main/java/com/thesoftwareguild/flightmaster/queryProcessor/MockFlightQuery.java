/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryProcessor;

import com.thesoftwareguild.flightmaster.controllers.RequestController;
import com.thesoftwareguild.flightmaster.models.Flight;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;



public class MockFlightQuery implements FlightQuery {

    final static Logger logger = Logger.getLogger(MockFlightQuery.class);
    
    private int requestId;
    private int adults;
    private int children;
    private int seniors;
    private int infants;
    private int maxStops;
    private String origin;
    private String dest;
    private Date depart;
    private Date returnDate;
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }

    // returns a random list of 10 flights
    @Override
    public List<Flight> execute() throws IOException {
        DecimalFormat df = new DecimalFormat("#####.##");
        List<Flight> retList = new ArrayList<>();
        Date timeOfQuery = new Date(System.currentTimeMillis());
        //System.out.println("Flight Query Executed: " + origin + " to " + dest);
        for (int i = 0; i < 10; i++) {
            Flight flight1 = new Flight();
            flight1.setOrigin(origin);
            flight1.setDestination(dest);
            flight1.setRequestId(requestId);
            
            flight1.setPrice(Math.random() * 100 + 1);
            flight1.setFightId(new Integer(i+1).toString());
            flight1.setDuration((int) (60 + Math.random() * 400));
            flight1.addFlightLeg(this.nextSessionId(), new Integer((int) (Math.random() * 999)).toString(), "AA");
            flight1.setQueryTime(timeOfQuery);
            flight1.setCarrier("AA");
            retList.add(flight1);
        }
// Reset all parameters
        requestId = 0;
        adults = 0;
        children = 0;
        seniors = 0;
        infants = 0;
        maxStops = 0;
        origin = null;
        dest = null;
        depart = null;
        returnDate = null;
        return retList;
    }

	@Override
	public void populateData(Map<String, Object> data) {
		// TODO Auto-generated method stub
		
	}



}
