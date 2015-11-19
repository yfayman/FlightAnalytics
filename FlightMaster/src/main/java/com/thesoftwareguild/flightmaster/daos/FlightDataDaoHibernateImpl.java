/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.Flight;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author yan
 */
public class FlightDataDaoHibernateImpl implements FlightDataDao {
    
    private SessionFactory sessionFactory;

    @Override
    public void addFlightDataToRequestById(int requestId, List<Flight> flights) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
