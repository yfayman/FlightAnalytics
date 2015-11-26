/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import com.thesoftwareguild.flightmaster.queryProcessor.FlightLeg;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

/**
 * This is the data model which reflects what will be written in flights table
 * @author Yan
 */
@Entity
@Table(name = "requests")
@SecondaryTables({@SecondaryTable(
        name = "requestdata", 
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "request_id", referencedColumnName = "id")),
    @SecondaryTable(
            name="flightdata",
            pkJoinColumns = @PrimaryKeyJoinColumn(name="requestdata_id", referencedColumnName = "id"))})
public class Flight implements Serializable {
    
    @Id
    private int requestId;
    
    private String fightId;
    private String origin;
    private String destination;
    private String carrier;
    private Date queryTime;
    private List<FlightLeg> flightlegs;
    private double price; 
    private int duration;

    public Flight(){
        this.flightlegs = new ArrayList<>();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public void addFlightLeg(String bookingCode, String flightNum, String flightCarrier){
        flightlegs.add(new FlightLeg(bookingCode, flightNum, flightCarrier));
    }
    
    public String getBookingCodeAtIndex(int i){
        return flightlegs.get(i).getBookingCode();
    }
    
    public String getFlightNumAtIndex(int i){
        return flightlegs.get(i).getFlightNum();
    }
    public String getFlightCarrierAtIndex(int i){
        return flightlegs.get(i).getCarrier();
    }

    public String getFightId() {
        return fightId;
    }

    public void setFightId(String fightId) {
        this.fightId = fightId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<FlightLeg> getFlightlegs() {
        return flightlegs;
    }

    public void setFlightlegs(List<FlightLeg> flightlegs) {
        this.flightlegs = flightlegs;
    }

    public Date getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }    
}
