/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;


import com.thesoftwareguild.flightmaster.models.Flight;
import com.thesoftwareguild.flightmaster.models.RequestParameters;
import java.util.List;

/**
 *
 * @author Yan
 */
public interface RequestDao {
    
    public RequestParameters add(RequestParameters requestData);
    
    public void addFlights(int requestId, List<Flight> flights);
    
    public void delete(int requestId);
    
    public RequestParameters getRequestByRequestId(int requestId);
    
    public List<RequestParameters> getRequestsByUserId(int userId);
    
    public List<Flight> getDataByRequestId(int requestId);
    
    public List<RequestParameters> getLiveRequests();
     
    
}
