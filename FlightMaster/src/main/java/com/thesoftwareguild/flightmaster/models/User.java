/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author yan
 */
public class User {
    
    private int userId;
    
    @NotBlank
    @Size(max = 20)
    private String firstName;
    
    @NotBlank
    @Size(max = 20)
    private String lastName;
    
    @NotBlank
    @Size(max = 20)
    private String username;
    
    @NotBlank
    @Size(max = 20)
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    
    @NotBlank
    @Size(min = 6, max = 15)
    private String password;
    
    private List<String> authorities = new ArrayList<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return authorities;
    }

    public void setRoles(List<String> roles) {
        this.authorities = roles;
    }
    
    public void addAuthority(String authority){
        authorities.add(authority);
    }
    
    public void deleteAuthority(String authority){
        authorities.remove(authority);
    }
    
    
    
    
    
    
    
    
    
    
}
