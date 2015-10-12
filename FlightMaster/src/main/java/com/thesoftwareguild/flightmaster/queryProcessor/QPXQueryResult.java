/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class QPXQueryResult implements FlightQueryResult{
    
  private String id;  
  private String price;
  private Integer duration;
  private List<Flight> flights = new ArrayList<>();

  @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

  @Override
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

  @Override
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Flight> getFlights() {
        return flights;
    }

   public void addFlight(Flight flight){
       flights.add(flight);
   }
   
   public void removeFlight(Flight flight){
        flights.remove(flight);
   }
    
  
  
}
