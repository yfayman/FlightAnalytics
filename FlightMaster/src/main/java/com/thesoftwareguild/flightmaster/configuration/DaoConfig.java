/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.configuration;


import com.thesoftwareguild.flightmaster.daos.AirportDataDaoHibernateImpl;
import com.thesoftwareguild.flightmaster.daos.AirportDataDaoJdbcImpl;
import com.thesoftwareguild.flightmaster.daos.RequestDaoHibernateImpl;
import com.thesoftwareguild.flightmaster.daos.RequestDaoJdbcImpl;
import com.thesoftwareguild.flightmaster.daos.UserDaoHibernateImpl;
import com.thesoftwareguild.flightmaster.daos.UserDaoJdbcImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author yan
 */

@Configuration
public class DaoConfig {
    
    @Autowired
    private DriverManagerDataSource datasource;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private SessionFactory sessionFactory;
   
    
    @Bean
    public DriverManagerDataSource datasource(){
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setDriverClassName("com.mysql.jdbc.Driver");
        dmds.setUrl("jdbc:mysql://localhost:3306/flights");
        dmds.setUsername("root");
        dmds.setPassword("");
        return dmds;
    }
    
    @Bean
    public DataSourceTransactionManager getTransactionManager(){
        DataSourceTransactionManager dstm = new DataSourceTransactionManager();
        dstm.setDataSource(datasource);
        return dstm;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(){
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(datasource);
        return jdbcTemplate;
    }
    
    @Bean
    public RequestDaoJdbcImpl requestDaoJdbc(){
        RequestDaoJdbcImpl dao = new RequestDaoJdbcImpl();
        dao.setJdbcTemplate(jdbcTemplate);
        return dao;
    }
    

    
    @Bean
    public UserDaoJdbcImpl userDaoJdbc(){
        UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
        dao.setJdbcTemplate(jdbcTemplate);
        return dao;
    }
    
    @Bean
    public UserDaoHibernateImpl userDaoHibernate(){
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
        dao.setSessionFactory(sessionFactory);
        return dao;
    }
    
    @Bean
    public RequestDaoHibernateImpl requestDaoHibernate(){
        RequestDaoHibernateImpl dao = new RequestDaoHibernateImpl();
        dao.setSessionFactory(sessionFactory);
        return dao;
    }
    
    @Bean
    public AirportDataDaoJdbcImpl airportDataDaoJdbc(){
        AirportDataDaoJdbcImpl dao = new AirportDataDaoJdbcImpl();
        dao.setJdbcTemplate(jdbcTemplate);
        return dao;
    }
    
    @Bean
    public AirportDataDaoHibernateImpl airportDataDaoHibernate(){
        AirportDataDaoHibernateImpl dao = new AirportDataDaoHibernateImpl();
        dao.setSessionFactory(sessionFactory);
        return dao;
    }
     
}
