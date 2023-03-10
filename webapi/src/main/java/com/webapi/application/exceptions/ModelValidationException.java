
package com.webapi.application.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelValidationException extends RuntimeException {
    private Map<String, List<String>> errors;

    public ModelValidationException() {
        
    }
    
    public ModelValidationException(String message) {
        this("", message);
    }

    public ModelValidationException(String key, String message) {
        errors = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add(message);
        errors.put(key, list);
    }
    
    public ModelValidationException(Map<String, List<String>> errors) {
        this.errors = errors;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }
}
