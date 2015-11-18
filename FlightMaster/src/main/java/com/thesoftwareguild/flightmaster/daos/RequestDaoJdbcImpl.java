/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.Flight;
import com.thesoftwareguild.flightmaster.models.RequestParameters;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * This writes information about requests into the database using JDBCTemplate
 * This includes Origin, Destination, # of passengers, departure 
 * and arrival date.
 * @author yan
 */
public class RequestDaoJdbcImpl implements RequestDao {
    
    JdbcTemplate jdbcTemplate;
    
    private static final String SQL_GET_LAST_ID = "SELECT LAST_INSERT_ID()";
    private static final String SQL_ADD_REQUEST = "INSERT INTO requests (user_id, origin, destination, depart_date, return_date, adult_passengers, child_passengers, senior_passengers, max_stops, interval , queries_left) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RequestParameters add(RequestParameters requestData) {
        jdbcTemplate.update(SQL_ADD_REQUEST, requestData.getUserId(),
                requestData.getOrigin(),
                requestData.getDestination(),
                requestData.getDepDate(),
                requestData.getRetDate(),
                requestData.getAdultPassengers(),
                requestData.getChildPassengers(),
                requestData.getSeniorPassengers(),
                requestData.getMaxStops(),
                requestData.getInterval(),
                requestData.getNumberQueries());
        requestData.setRequestId(jdbcTemplate.queryForObject(SQL_GET_LAST_ID, Integer.class));
        
        return requestData;
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RequestParameters> getRequestByUserId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RequestParameters getRequestByRequestId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Flight> getDataByRequestId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
}
