package com.thesoftwareguild.flightmaster.controllers;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class IndexController {
    
    final static Logger logger = Logger.getLogger(IndexController.class);
        
    @RequestMapping(value="", method = RequestMethod.GET)
    public String loadIndex(Map<String, Object> model) {
        return "index";
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String afterLogIn(){
        return "index";
    }  
    
    @RequestMapping(value="loginfail", method = RequestMethod.GET)
    public String loadaIndex(Model model) {
        model.addAttribute("loginFail", new Object());
        return "index";
    }
}
