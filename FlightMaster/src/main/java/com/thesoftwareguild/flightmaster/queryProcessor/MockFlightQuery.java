/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryProcessor;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class MockFlightQuery implements FlightQuery {

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

    @Override
    public List<Flight> execute() throws IOException {
        DecimalFormat df = new DecimalFormat("#####.##");
        List<Flight> retList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Flight flight1 = new Flight();
            flight1.setPrice(df.format(Math.random() * 100 + 1));
            flight1.setFightId(new Integer(i+1).toString());
            flight1.setDuration((int) (60 + Math.random() * 400));
            flight1.addFlightLeg(this.nextSessionId(), new Integer((int) (Math.random() * 999)).toString(), "AA");
            retList.add(flight1);
        }

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
        this.dest = dest;
    }

    @Override
    public Date getDepartDate() {
        return depart;
    }

    @Override
    public void setDepartDate(Date departDate) {
        this.depart = depart;
    }

    @Override
    public Date getReturnDate() {
        return returnDate;
    }

    @Override
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

}
