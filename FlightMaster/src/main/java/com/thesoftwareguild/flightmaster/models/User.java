/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author yan
 */
@Entity
@Table(name = "users")
public class User implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int userId;
    
    @NotBlank
    @Size(max = 20)
    @Column(name="first_name", nullable = false, length = 20)
    private String firstName;
    
    @NotBlank
    @Size(max = 20)
    @Column(name="last_name", nullable = false, length = 20)
    private String lastName;
    
    @NotBlank
    @Size(max = 20)
    @Column(name="username", nullable = false, length = 20,unique = true)
    private String username;
    
    @NotBlank
    @Size(max = 20)
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @Column(name="email", nullable = false, length = 20)
    private String email;
    
    @NotBlank
    @Size(min = 6, max = 15)
    @Column(name="password", nullable = false, length = 60)
    private String password;
    
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "username"))
    @Column(name="authority")
    private List<String> authorities = new ArrayList<>();

    @Column(name = "enabled")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled;
    
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

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    
    
    
    
    
    
    
    
    
    
}
