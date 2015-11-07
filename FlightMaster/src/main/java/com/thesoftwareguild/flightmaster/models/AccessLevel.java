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
public enum AccessLevel {
    USER("USER"),
    ADMIN("ADMIN");
    
    private String accessLevel;

    public String getAccessLevel() {
        return accessLevel;
    }

    private AccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
    
    
}
