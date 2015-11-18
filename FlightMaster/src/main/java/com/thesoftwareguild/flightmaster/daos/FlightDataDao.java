/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.Flight;
import java.util.List;

/**
 * Adds to the flightdata table.
 * @author yan
 */
public interface FlightDataDao {
    public void addFlightDataToRequestById(int requestId, List<Flight> flights);
}
