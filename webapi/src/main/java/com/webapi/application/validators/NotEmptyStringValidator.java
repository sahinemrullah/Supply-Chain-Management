package com.webapi.application.validators;

import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.IValidator;
import com.webapi.application.utils.StringUtils;

public class NotEmptyStringValidator implements IValidator {
    
    private final String value;
    private final String key;
    private final String message;
    
    public NotEmptyStringValidator(String key, String value, String message) {
        this.value = value;
        this.key = key;
        this.message = message;
    }

    @Override
    public void validate(IResult result) {
        if (StringUtils.isBlank(value)) {
            result.addError(key, message);
        }
    }

}
