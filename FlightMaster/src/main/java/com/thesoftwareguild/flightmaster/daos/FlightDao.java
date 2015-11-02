/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.Flight;
import java.util.List;

/**
 * Saves flight Data
 * @author Yan
 */
public interface FlightDao {
    
    
    public void add(List<Flight> flights, int requestorId);
    public List<Flight> get(int requestorId);
    
}
