/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.AirportData;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Handles airport data which is relayed to a request form
 * @author yan
 */
public class AirportDataDaoJdbcImpl implements AirportDataDao {

    private static final String SQL_GET_ALL_IATA_CODES = "SELECT iata FROM airportdata";
    
    private JdbcTemplate jdbcTemplate;
    
    /*
        At present, the only data being pulled from the database is IATA codes
    */
    @Override
    @Cacheable(value = "airportCache")
    @Transactional(propagation = Propagation.REQUIRED)
    public AirportData getAllAirports() {
        AirportData airportData= new AirportData();
        List<String> iataCodes = jdbcTemplate.queryForList(SQL_GET_ALL_IATA_CODES, String.class);
        airportData.setIataCodes(iataCodes);
        return airportData;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    
}
