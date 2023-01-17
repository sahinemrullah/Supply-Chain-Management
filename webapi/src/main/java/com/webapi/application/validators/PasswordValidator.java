package com.webapi.application.validators;

public class PasswordValidator extends RegexValidator {
    
    private static final String PASSWORD_IS_NULL = "Şifre boş bırakılamaz.";
    private static final String PASSWORD_IS_INVALID = "Şifre en az 8 karakter, en az 1 küçük harf, en az 1 büyük harf ve en az 1 sayı içermelidir.";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    private static final String PASSWORD_KEY = "password";
    
    public PasswordValidator(String email) {
        super(PASSWORD_KEY, email, PASSWORD_PATTERN, PASSWORD_IS_NULL, PASSWORD_IS_INVALID);
    }
}
