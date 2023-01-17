
package com.webapi.application.abstractions;

import com.webapi.application.exceptions.ModelValidationException;

public interface IJsonParser {
    public <T> T fromJson(String jsonString, Class<T> classOfT) throws ModelValidationException;
    public <T> String toJson(T model);
}
