package com.webapi.application.concretes;

import com.webapi.application.abstractions.IResult;
import com.webapi.application.exceptions.ModelValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result<T> implements IResult<T> {

    private T item;
    private Map<String, List<String>> errors;
    
    public Result() {
        item = null;
        errors = new HashMap<>();
    }

    @Override
    public T getItem() {
        return item;
    }

    @Override
    public Map<String, List<String>> getErrors() {
        return errors;
    }

    @Override
    public void addError(String key, String message) {
        if (!errors.containsKey(key)) {
            errors.put(key, new ArrayList<>());
        }

        errors.get(key).add(message);
    }

    @Override
    public boolean isSucceeded() {
        return errors.isEmpty();
    }

    @Override
    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public void throwIfNotSucceeded() {
        if(!isSucceeded())
            throw new ModelValidationException(errors);
    }

}
