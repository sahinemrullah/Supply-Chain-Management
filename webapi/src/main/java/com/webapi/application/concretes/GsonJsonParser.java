
package com.webapi.application.concretes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.abstractions.IJsonParser;
import com.webapi.application.exceptions.ModelValidationException;

public final class GsonJsonParser implements IJsonParser {
    
    private static final Gson GSON = new GsonBuilder().create();
    
    @Override
    public <T> T fromJson(String jsonString, Class<T> classOfT) throws ModelValidationException {
        return GSON.fromJson(jsonString, classOfT);
    }

    @Override
    public <T> String toJson(T model) {
        return GSON.toJson(model);
    }

}
