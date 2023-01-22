package com.webapi.application.requests.supplierregister;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.persistence.abstractions.ISQLOperation;
import java.sql.SQLException;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.validators.EmailValidator;
import com.webapi.application.validators.NotEmptyStringValidator;
import com.webapi.application.validators.PasswordValidator;
import com.webapi.application.validators.PhoneNumberValidator;
import com.webapi.application.validators.StringsMustBeEqualValidator;

public class SupplierRegisterRequestHandler implements IRequestHandler<SupplierRegisterRequest, Void> {

    private static final String PASSWORD_VERIFICATION_KEY = "passwordVerification";
    private static final String PASSWORD_VERIFICATION_EMPTY_MESSAGE = "Şifre tekrarı boş bırakılamaz.";
    private static final String PASSWORDS_NOT_EQUAL_MESSAGE = "Şifreler eşleşmiyor.";
    private static final String NAME_KEY = "name";
    private static final String NAME_EMPTY_MESSAGE = "İsim boş bırakılamaz.";
    private static final String EMAIL_KEY = "email";

    @Override
    public IResult<Void> handle(SupplierRegisterRequest request) throws SQLException {
        ISQLOperation<String, Boolean> supplierExistsQuery = new SupplierExistsQuery();
        return ResultBuilder.create(request, Void.class)
                .withValidator(new EmailValidator(request.getEmail()))
                .withValidator(new PasswordValidator(request.getPassword()))
                .withValidator(new NotEmptyStringValidator(NAME_KEY, request.getName(), NAME_EMPTY_MESSAGE))
                .withValidator(new PhoneNumberValidator(request.getPhoneNumber()))
                .withValidator(new NotEmptyStringValidator(PASSWORD_VERIFICATION_KEY, request.getPasswordVerification(), PASSWORD_VERIFICATION_EMPTY_MESSAGE))
                .withValidator(new StringsMustBeEqualValidator(PASSWORD_VERIFICATION_KEY, request.getPasswordVerification(), request.getPassword(), PASSWORDS_NOT_EQUAL_MESSAGE))
                .validate()
                .check(!supplierExistsQuery.execute(request.getEmail()))
                    .withException(new ModelValidationException(EMAIL_KEY, "Bu email adresi ile ilişkili bir satıcı bulunmaktadır."))
                .check(new SupplierRegisterCommand())
                    .withError("",  "Bilinmeyen bir hatadan dolayı kullanıcı oluşturulamadı.")
                .build();
    }

}
