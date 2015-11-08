/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.Flight;
import com.thesoftwareguild.flightmaster.models.User;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Writes flight data to a database using JDBC Template. 
 * This data is connected to the requestor through a bridge table
 * @author Yan
 */
public class UserDaoJdbcImpl implements UserDao{
    
    private static final String SQL_GET_LAST_ID = "SELECT LAST_INSERT_ID()";
    private static final String SQL_ADD_USER = "INSERT INTO users (username, password, first_name, last_name, email ) VALUES(?, ?, ?, ?, ?)";
    
    private static final String SQL_ADD_AUTHORITY = "INSERT INTO authorities (username, authority) VALUES (?,?)";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User getByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User getById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User addUser(User newUser) {
        // Add to users
        jdbcTemplate.update(SQL_ADD_USER,
                newUser.getUsername(),
                newUser.getPassword(),
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail());
        Integer id = jdbcTemplate.queryForObject(SQL_GET_LAST_ID, Integer.class);
        newUser.setUserId(id);
        // add Authorities
        for(String authority : newUser.getRoles())
            jdbcTemplate.update(SQL_ADD_AUTHORITY, newUser.getUsername(), authority);
        return newUser;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
    
}
