package com.webapi.application.validators;

import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.IValidator;
import com.webapi.application.utils.StringUtils;

public class RegexValidator implements IValidator {
    
    private final String value;
    private final String key;
    private final String isNull;
    private final String isInvalid;
    private final String pattern;
    
    public RegexValidator(String key, String value, String pattern, String isNull, String isInvalid) {
        this.value = value;
        this.key = key;
        this.isNull = isNull;
        this.isInvalid = isInvalid;
        this.pattern = pattern;
    }

    @Override
    public void validate(IResult result) {
        if (StringUtils.isNull(value)) {
            result.addError(key, isNull);
        } else if (!StringUtils.patternMatches(value, pattern)) {
            result.addError(key, isInvalid);
        }
    }

}
