/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author yan
 */
@Entity
@Table(name = "profile")
public class UserProfile {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id; 
 
    @Column(name="TYPE", length=15, unique=true, nullable=false)
    private String type = AccessLevel.USER.getAccessLevel();
    
        @Column(name="STATE", nullable=false)
    private String state=State.ACTIVE.getState();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
