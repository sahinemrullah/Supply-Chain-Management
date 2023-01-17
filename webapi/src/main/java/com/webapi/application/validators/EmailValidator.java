package com.webapi.application.validators;

public class EmailValidator extends RegexValidator {
    
    private static final String EMAIL_IS_NULL = "Mail adresi boş bırakılamaz.";
    private static final String EMAIL_IS_INVALID = "Lütfen geçerli bir mail adresi giriniz.";
    private static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String EMAIL_KEY = "email";
    
    public EmailValidator(String email) {
        super(EMAIL_KEY, email, EMAIL_PATTERN, EMAIL_IS_NULL, EMAIL_IS_INVALID);
    }
}
