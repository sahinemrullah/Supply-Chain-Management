package com.webapi.application.validators;

public class PhoneNumberValidator extends RegexValidator {
    
    private static final String PHONE_NUMBER_IS_NULL = "Telefon numarası boş bırakılamaz.";
    private static final String PHONE_NUMBER_IS_INVALID = "Geçerli bir telefon numarası giriniz. Öreneğin: 1234567890";
    private static final String PHONE_NUMBER_PATTERN = "^(?=.*[0-9]).{10,10}$";
    private static final String PHONE_NUMBER_KEY = "phoneNumber";
    
    public PhoneNumberValidator(String email) {
        super(PHONE_NUMBER_KEY, email, PHONE_NUMBER_PATTERN, PHONE_NUMBER_IS_NULL, PHONE_NUMBER_IS_INVALID);
    }
}
