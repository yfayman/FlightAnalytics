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
    public int getAdultPassengers() {
        return adults;
    }

    @Override
    public void setAdultPassengers(int adultPassengers) {
        this.adults = adults;
    }

    @Override
    public int getChildPassengers() {
        return children;
    }

    @Override
    public void setChildPassengers(int childPassengers) {
        this.children = childPassengers;
    }

    @Override
    public int getSeniorPassengers() {
        return seniors;
    }

    @Override
    public void setSeniorPassengers(int seniorPassengers) {
        this.seniors = seniorPassengers;
    }

    @Override
    public int getInfantInSeatCount() {
        return infants;
    }

    @Override
    public void setInfantInSeatCount(int infantInSeatCount) {
        this.infants = infantInSeatCount;
    }

    @Override
    public int getMaxStops() {
        return maxStops;
    }

    @Override
    public void setMaxStops(int maxStops) {
        this.maxStops = maxStops;
    }

    @Override
    public String getOrigin() {
        return origin;
    }

    @Override
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String getDestination() {
        return dest;
    }

    @Override
    public void setDestination(String destination) {
        this.dest = destination;
    }

    @Override
    public Date getDepartDate() {
        return depart;
    }

    @Override
    public void setDepartDate(Date departDate) {
        this.depart = departDate;
    }

    @Override
    public Date getReturnDate() {
        return returnDate;
    }

    @Override
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public int getRequestId() {
        return requestId;
    }

}
