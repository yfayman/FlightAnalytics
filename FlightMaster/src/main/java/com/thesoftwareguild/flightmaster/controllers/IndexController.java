package com.thesoftwareguild.flightmaster.controllers;

import com.thesoftwareguild.flightmaster.models.User;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        return "index";
    }
    
//    @RequestMapping(value = "j_spring_security_logout")
//    public String afterLogOut(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        authentication.setAuthenticated(false);
//        return "index";
//    }
    
}
