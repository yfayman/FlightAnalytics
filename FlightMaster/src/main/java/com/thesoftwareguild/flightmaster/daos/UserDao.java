/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.User;

/**
 *
 * @author yan
 */
public interface UserDao {
    
    User findById(int id);
     
    User findByUsername(String username);
    
}
