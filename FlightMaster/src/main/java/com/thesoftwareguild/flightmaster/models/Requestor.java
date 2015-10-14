/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

/**
 *
 * @author yan
 */
public interface Requestor {
    
    // There are requests to be made
    public boolean hasRequest();
    
    // to be called after a request is complete
    public void requestMade();
    
    public long getInterval();
    
}
