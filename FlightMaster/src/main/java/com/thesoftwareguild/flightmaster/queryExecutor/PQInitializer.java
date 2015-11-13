/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryExecutor;

import java.util.PriorityQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author yan
 */
@Configuration
public class PQInitializer {
    
    @Bean
    public PriorityQueue<Request> getPQ(){
        return new PriorityQueue<Request>(10, Request.flightQuerySoonest);
    }
    
}
