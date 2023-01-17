package com.webapi.application.abstractions;

import java.util.List;
import java.util.Map;

public interface IResult<T extends Object> {

    public T getItem();

    public Map<String, List<String>> getErrors();

    public void addError(String key, String message);

    public boolean isSucceeded();

    public void setItem(T success);
}
