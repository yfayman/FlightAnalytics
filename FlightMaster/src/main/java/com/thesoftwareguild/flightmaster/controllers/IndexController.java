package com.thesoftwareguild.flightmaster.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
        
    public IndexController() {
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String loadIndex(Map<String, Object> model) {
        model.put("message", "Hello from the controller" );
        return "index";
    }
    
    @RequestMapping(value="register", method = RequestMethod.GET)
    public String loadRegister(Map<String, Object> model) {
        model.put("message", "Hello from the controller" );
        return "register";
    }
}
