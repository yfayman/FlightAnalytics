/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.queryExecutor;

import javax.annotation.PostConstruct;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author yan
 */

public interface Executor extends ApplicationContextAware {

    
    void addToExecutor(Request request);

    void run();
    
}
