/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.airportdatabasebuilder;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author yan
 */
public class Dao {
    

    private DriverManagerDataSource datasource;
    
  
    private JdbcTemplate jdbcTemplate;
    
    public Dao(){
        datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/flights");
        datasource.setUsername("root");
        datasource.setPassword("");
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(datasource);
        
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    
    
    
}
