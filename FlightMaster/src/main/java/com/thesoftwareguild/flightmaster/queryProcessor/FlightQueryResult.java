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
public interface FlightQueryResult {
    // Price includes the currency type in prefix or suffix
    public String getPrice();
    public String getId();
    // Duration in minutes
    public Integer getDuration();
}
