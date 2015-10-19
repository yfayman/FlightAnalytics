/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryProcessor;

/**
 *
 * @author apprentice
 */
public class FlightLeg {
    private String bookingCode;
    private String flightNum;
    private String carrier;

    public FlightLeg(String bookingCode, String flightNum, String carrier) {
        this.bookingCode = bookingCode;
        this.flightNum = flightNum;
        this.carrier = carrier;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
    
    
    
}
