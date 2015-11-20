/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.Flight;
import com.thesoftwareguild.flightmaster.models.RequestParameters;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
    private static final String SQL_GET_REQUESTS_BY_USERID = "SELECT * FROM requests WHERE user_id = ?";
    private static final String SQL_GET_REQUEST_BY_ID = "SELECT * FROM requests WHERE id = ?";
    private static final String SQL_DELETE_REQUEST_BY_ID = "DELETE FROM requests WHERE id = ?";

    private static final String SQL_ADD_REQUESTDATA_POINT = "INSERT INTO requestdata (request_id, datetime_of_query) VALUES(?, ?)";
    private static final String SQL_ADD_FLIGHT_DATA = "INSERT INTO flightdata (requestdata_id, price, carrier) VALUES(?, ?, ?)";

    private static final String SQL_GET_FLIGHTDATA_BY_REQUEST_ID =  "SELECT requests.id, requests.origin, requests.destination, requestdata.datetime_of_query, flightdata.price, flightdata.carrier FROM requests "
            + "INNER JOIN requestdata ON requests.id = requestdata.request_id "
            + "INNER JOIN flightdata ON requestdata.id = flightdata.requestdata_id WHERE requests.id = ?";
    
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
    public void delete(int requestId) {
        jdbcTemplate.update(SQL_DELETE_REQUEST_BY_ID, requestId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<RequestParameters> getRequestsByUserId(int userId) {
        return jdbcTemplate.query(SQL_GET_REQUESTS_BY_USERID, new RequestMapper(), userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RequestParameters getRequestByRequestId(int requestId) {
        return jdbcTemplate.queryForObject(SQL_GET_REQUEST_BY_ID, new RequestMapper(), requestId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Flight> getDataByRequestId(int requestId) {
        return jdbcTemplate.query(SQL_GET_FLIGHTDATA_BY_REQUEST_ID, new FlightMapper(), requestId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addFlights(int requestId, List<Flight> flights) {
        if (flights != null && flights.size() > 0) {
            Date timeOfQuery = flights.get(0).getQueryTime();
            jdbcTemplate.update(SQL_ADD_REQUESTDATA_POINT, requestId, timeOfQuery);
            int requestDataId = jdbcTemplate.queryForObject(SQL_GET_LAST_ID, Integer.class);
            jdbcTemplate.batchUpdate(SQL_ADD_FLIGHT_DATA, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, requestDataId);
                    ps.setDouble(2, flights.get(i).getPrice());
                    ps.setString(3, flights.get(i).getCarrier());
                }

                @Override
                public int getBatchSize() {
                    return flights.size();
                }
            });
        }
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
    
    private final class FlightMapper implements RowMapper<Flight> {

        @Override
        public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
            Flight flight = new Flight();
            flight.setCarrier(rs.getString("carrier"));
            flight.setPrice(rs.getDouble("price"));
            flight.setQueryTime(rs.getTimestamp("datetime_of_query"));
            flight.setOrigin(rs.getString("origin"));
            flight.setDestination(rs.getString("destination"));
            flight.setRequestId(rs.getInt("id"));
            return flight;
        }
        
    }

}
