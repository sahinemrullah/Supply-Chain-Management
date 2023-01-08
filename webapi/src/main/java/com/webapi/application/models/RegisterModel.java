package com.webapi.application.models;

import com.google.gson.annotations.Expose;
import com.webapi.application.abstractions.IValidatable;
import com.webapi.application.abstractions.IValidation;
import com.webapi.application.concretes.Validation;
import com.webapi.application.utils.StringUtils;

public class RegisterModel implements IValidatable {
	
	@Expose
	private String name;
	
	@Expose
	private String phoneNumber;
	
	@Expose
	private String email;
	
	@Expose
	private String password;
	
	@Expose
	private String passwordVerification;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordVerification() {
		return passwordVerification;
	}

	public void setPasswordVerification(String passwordVerification) {
		this.passwordVerification = passwordVerification;
	}

	public RegisterModel() {
		
	}
	
	@Override
	public IValidation validate() {
		IValidation validationResult = new Validation();
		
		if(!StringUtils.isNull(email))	{
			if(!StringUtils.patternMatches(email, "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
				validationResult.addError("email", "Lütfen geçerli bir mail adresi giriniz.");
			}
		} else {
			validationResult.addError("email", "Mail adresi boş bırakılamaz.");
		}
		
		if(StringUtils.isEmptyOrWhiteSpace(name))
		{
			validationResult.addError("name", "İsim boş bırakılamaz.");
		}
		
		if(!StringUtils.isNull(phoneNumber)) {
			if(!StringUtils.patternMatches(phoneNumber, "^(?=.*[0-9]).{10,10}$")) {
				validationResult.addError("phoneNumber", "Geçerli bir telefon numarası giriniz.");
			}
		} else {
			validationResult.addError("phoneNumber", "Telefon numarası boş bırakılamaz.");
		}
		
		if(!StringUtils.isNull(password)) {
			if(!StringUtils.patternMatches(password, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$")) {
				validationResult.addError("password", "Şifre en az 8 karakter, en az 1 küçük harf, en az 1 büyük harf ve en az 1 sayı içermelidir.");
			}
		} else {
			validationResult.addError("password", "Şifre boş bırakılamaz.");
		}
		
		if(!StringUtils.isNull(passwordVerification)) {
			if(!StringUtils.isEqual(password, passwordVerification)) {
				validationResult.addError("password", "Şifreler eşleşmiyor.");
				validationResult.addError("passwordVerification", "Şifreler eşleşmiyor.");
			}
		} else {
			validationResult.addError("passwordVerification", "Şifre tekrarı boş bırakılamaz.");
		}
		
		return validationResult;
	}
	
}
