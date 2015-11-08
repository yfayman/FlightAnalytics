/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.Flight;
import com.thesoftwareguild.flightmaster.models.User;
import java.util.List;

/**
 * Saves flight Data
 * @author Yan
 */
public interface UserDao {
    
     public User getByUsername(String username);
     public User getById(Integer id);
     public List<User> list();
     public User addUser(User newUser);
     public void deleteUser(String username);
     public void updateUser(User user);
     
     
     
}
