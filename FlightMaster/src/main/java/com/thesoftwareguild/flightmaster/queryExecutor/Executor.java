/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryExecutor;

import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author yan
 */

public interface Executor extends ApplicationContextAware {

    
    void addToExecutor(Request request);

    void run();
    
}
