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
    
    public void delete(int id);
    
    public RequestParameters getRequestByRequestId(int id);
    
    public List<RequestParameters> getRequestByUserId(int id);
    
    public List<Flight> getDataByRequestId(int id);
    
    
}
