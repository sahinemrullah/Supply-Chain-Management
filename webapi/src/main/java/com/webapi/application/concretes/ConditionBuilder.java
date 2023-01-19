
package com.webapi.application.concretes;

public class ConditionBuilder {
    private ResultBuilder parent;
    private boolean condition;
    ConditionBuilder(ResultBuilder parent, boolean condition) {
        this.parent = parent;
        this.condition = condition;
    }
    
    public ResultBuilder withException(RuntimeException exception) {
        if(!condition)
            throw exception;
        
        return parent;
    }
    
    public ResultBuilder withError(String key, String message) {
        if(!condition)
            parent.addError(key, message);
        
        return parent;
    }
    
}
