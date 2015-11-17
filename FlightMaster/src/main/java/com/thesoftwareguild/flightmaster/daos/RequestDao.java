/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.RequestData;
import java.util.List;

/**
 *
 * @author Yan
 */
public interface RequestDao {
    
    public RequestData add(RequestData requestData);
    
    public void delete(int id);
    
    public RequestData getById(int id);
    
    public List<RequestData> getByUserId(int id);
    
    
}
