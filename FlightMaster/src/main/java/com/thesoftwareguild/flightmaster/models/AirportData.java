/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import java.util.List;

/**
 *
 * @author yan
 */
public class AirportData {
    
    public List<String> iataCodes;

    public List<String> getIataCodes() {
        return iataCodes;
    }

    public void setIataCodes(List<String> iataCodes) {
        this.iataCodes = iataCodes;
    }
    
    
}
