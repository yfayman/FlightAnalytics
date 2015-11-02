/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.Flight;
import java.util.List;

/**
 * Writes flight data to a database using JDBC Template. 
 * This data is connected to the requestor through a bridge table
 * @author Yan
 */
public class FlightDaoJdbcImpl implements FlightDao{

    @Override
    public void add(List<Flight> flights, int requestorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Flight> get(int requestorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
