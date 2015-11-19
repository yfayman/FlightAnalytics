/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.Flight;
import com.thesoftwareguild.flightmaster.models.RequestParameters;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This writes information about requests into the database using JDBCTemplate
 * This includes Origin, Destination, # of passengers, departure and arrival
 * date.
 *
 * @author yan
 */
public class RequestDaoJdbcImpl implements RequestDao {

    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_LAST_ID = "SELECT LAST_INSERT_ID()";
    private static final String SQL_ADD_REQUEST = "INSERT INTO requests (user_id, origin, destination, depart_date, return_date, adult_passengers, child_passengers, senior_passengers, max_stops, query_interval , queries_left) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_GET_REQUEST_BY_ID = "SELECT * FROM requests WHERE id = ?";
    private static final String SQL_DELETE_REQUEST_BY_ID = "DELETE FROM requests WHERE id = ?";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
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
        Integer id = jdbcTemplate.queryForObject(SQL_GET_LAST_ID, Integer.class);
        requestData.setRequestId(id);

        return requestData;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_REQUEST_BY_ID, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<RequestParameters> getRequestsByUserId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RequestParameters getRequestByRequestId(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_REQUEST_BY_ID, new RequestMapper(), id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Flight> getDataByRequestId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addFlights(int requestId, List<Flight> flights) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private final class RequestMapper implements RowMapper<RequestParameters> {

        @Override
        public RequestParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
            RequestParameters ret = new RequestParameters();
            ret.setRequestId(rs.getInt("id"));
            ret.setUserId(rs.getInt("user_id"));
            ret.setOrigin(rs.getString("origin"));
            ret.setDestination(rs.getString("destination"));
            ret.setDepDate(rs.getDate("depart_date"));
            ret.setRetDate(rs.getDate("return_date"));
            ret.setAdultPassengers(rs.getInt("adult_passengers"));
            ret.setChildPassengers(rs.getInt("child_passengers"));
            ret.setSeniorPassengers(rs.getInt("senior_passengers"));
            ret.setMaxStops(rs.getInt("max_stops"));
            ret.setInterval(rs.getInt("query_interval"));
            ret.setNumberQueries(rs.getInt("queries_left"));

            return ret;

        }

    }
   

}
