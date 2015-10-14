/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class MockFlightQuery implements FlightQuery{

    @Override
    public QPXFlightQueryResult execute() throws IOException {
        System.out.println("executed");
        return new QPXFlightQueryResult();
    }
    
}
