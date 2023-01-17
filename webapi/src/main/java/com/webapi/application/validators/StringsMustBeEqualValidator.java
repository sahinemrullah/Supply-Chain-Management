package com.webapi.application.validators;

import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.IValidator;
import com.webapi.application.utils.StringUtils;

public class StringsMustBeEqualValidator implements IValidator {
    
    private final String value;
    private final String key;
    private final String compareValue;
    private final String message;
    
    public StringsMustBeEqualValidator(String key, String value, String compareValue, String message) {
        this.value = value;
        this.key = key;
        this.compareValue = compareValue;
        this.message = message;
    }

    @Override
    public void validate(IResult result) {
        if(!StringUtils.isEqual(value, compareValue))
            result.addError(key, message);
    }

}
