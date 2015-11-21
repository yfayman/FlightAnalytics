/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.AirportData;
import org.hibernate.SessionFactory;

/**
 *
 * @author yan
 */
public class AirportDataDaoHibernateImpl implements AirportDataDao {
    
    public SessionFactory sessionFactory;

    @Override
    public AirportData getAllAirports() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
