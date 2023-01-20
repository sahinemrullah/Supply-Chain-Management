
package com.webapp.utils;

import java.io.IOException;
import java.util.Map;

public class RequestBuilder {
    private String url;
    private String token;
    
    private RequestBuilder() {
        
    }
    
    public static RequestBuilder create() {
        return new RequestBuilder();
    }
    
    public <T> Result post(T model) throws IOException {
        return HttpRequestUtils.post(url, model, token);
    }
    
    public Result get(Map<String, String> params) throws IOException {
        return HttpRequestUtils.get(url, params, token);
    }

    public RequestBuilder withURL(String url) {
        this.url = url;
        return this;
    }
    
    public RequestBuilder withToken(String token) {
        this.token = token;
        return this;
    }
    
}
