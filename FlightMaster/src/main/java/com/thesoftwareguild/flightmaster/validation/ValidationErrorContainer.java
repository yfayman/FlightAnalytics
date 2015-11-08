/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.validation;

import java.util.ArrayList;
import java.util.List;

/**
 *  Container that holds a List of Validation error messages
 * @author Yan
 */
public class ValidationErrorContainer {
    
    private List<ValidationError> validationErrors = new ArrayList<>();
    
    public void addValidationError(String field, String message){
        ValidationError error = new ValidationError(field,message);
        validationErrors.add(error);
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }
}
