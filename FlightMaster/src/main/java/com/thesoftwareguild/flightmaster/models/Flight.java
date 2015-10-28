/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import com.thesoftwareguild.flightmaster.queryProcessor.FlightLeg;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class Flight {
    private String fightId;
    private List<FlightLeg> flightlegs;
    private String price; // price var may include currency type
    private int duration;

    public Flight(){
        this.flightlegs = new ArrayList<>();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    
}
