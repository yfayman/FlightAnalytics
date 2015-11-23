/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.configuration;

import com.thesoftwareguild.flightmaster.queryProcessor.MockFlightQuery;
import com.thesoftwareguild.flightmaster.queryProcessor.QPXFlightQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author yan
 */
@Configuration
public class QueryConfig {
    
    @Bean
    MockFlightQuery mockFlightQuery(){
        return new MockFlightQuery();
    }
    
    @Bean
    QPXFlightQuery qpxFlightQuery(){
        return new QPXFlightQuery();
    }
    
    
    
}
