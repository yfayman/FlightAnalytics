/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.Flight;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author yan
 */
public class FlightDataDaoJdbcImpl implements FlightDataDao{

    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void addFlightDataToRequestById(int requestId, List<Flight> flights) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    
}
