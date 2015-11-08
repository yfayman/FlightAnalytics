package com.thesoftwareguild.flightmaster.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class IndexController {
        
    public IndexController() {
    }
    
    @RequestMapping(value="", method = RequestMethod.GET)
    public String loadIndex(Map<String, Object> model) {
        
        return "index";
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String afterLogIn(){
        return "redirect:/";
    }
    
}
