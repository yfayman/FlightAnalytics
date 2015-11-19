/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.controllers;

import com.thesoftwareguild.flightmaster.daos.UserDao;
import com.thesoftwareguild.flightmaster.models.User;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Yan
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private UserDao userDao;

//    public UserController(PasswordEncoder encoder, UserDao userDao) {
//        this.encoder = encoder;
//        this.userDao = userDao;
//    }

    public UserController() {
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public User register(@Valid @RequestBody User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        user.addAuthority("ROLE_USER");
        userDao.addUser(user);

        user.setPassword("placeholder"); // Not sending password back to client
        return user;
    }

    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
