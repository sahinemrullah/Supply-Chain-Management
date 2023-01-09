/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapi.application.validators;

import com.webapi.application.abstractions.IValidation;
import com.webapi.application.concretes.Validation;
import com.webapi.application.concretes.ValidatorBase;
import com.webapi.application.models.RegisterModel;
import com.webapi.application.utils.StringUtils;

public class RegisterModelValidator extends ValidatorBase<RegisterModel> {

    public RegisterModelValidator(RegisterModel model) {
        super(model);
    }

    public IValidation validate() {
        IValidation validationResult = new Validation();

        if (!StringUtils.isNull(model.getEmail())) {
            if (!StringUtils.patternMatches(model.getEmail(), "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
                validationResult.addError("email", "Lütfen geçerli bir mail adresi giriniz.");
            }
        } else {
            validationResult.addError("email", "Mail adresi boş bırakılamaz.");
        }

        if (StringUtils.isEmptyOrWhiteSpace(model.getName())) {
            validationResult.addError("name", "İsim boş bırakılamaz.");
        }

        if (!StringUtils.isNull(model.getPhoneNumber())) {
            if (!StringUtils.patternMatches(model.getPhoneNumber(), "^(?=.*[0-9]).{10,10}$")) {
                validationResult.addError("phoneNumber", "Geçerli bir telefon numarası giriniz.");
            }
        } else {
            validationResult.addError("phoneNumber", "Telefon numarası boş bırakılamaz.");
        }

        if (!StringUtils.isNull(model.getPassword())) {
            if (!StringUtils.patternMatches(model.getPassword(), "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$")) {
                validationResult.addError("password", "Şifre en az 8 karakter, en az 1 küçük harf, en az 1 büyük harf ve en az 1 sayı içermelidir.");
            }
        } else {
            validationResult.addError("password", "Şifre boş bırakılamaz.");
        }

        if (!StringUtils.isNull(model.getPasswordVerification())) {
            if (!StringUtils.isEqual(model.getPassword(), model.getPasswordVerification())) {
                validationResult.addError("passwordVerification", "Şifreler eşleşmiyor.");
            }
        } else {
            validationResult.addError("passwordVerification", "Şifre tekrarı boş bırakılamaz.");
        }

        return validationResult;
    }

}
