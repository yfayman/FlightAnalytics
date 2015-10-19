/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryProcessor;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlightQuery {
    public FlightQueryResult execute() throws IOException;
    
    public int getAdultPassengers();

    public void setAdultPassengers(int adultPassengers);

    public int getChildPassengers();

    public void setChildPassengers(int childPassengers);
    public int getSeniorPassengers();

    public void setSeniorPassengers(int seniorPassengers);

    public int getInfantInSeatCount();

    public void setInfantInSeatCount(int infantInSeatCount);

    public int getMaxStops();

    public void setMaxStops(int maxStops);

    public String getOrigin();

    public void setOrigin(String origin);
    public String getDestination();
    public void setDestination(String destination);

    public Date getDepartDate();

    public void setDepartDate(Date departDate);

    public Date getReturnDate();

    public void setReturnDate(Date returnDate);
}
