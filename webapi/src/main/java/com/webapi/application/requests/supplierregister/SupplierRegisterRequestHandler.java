package com.webapi.application.requests.supplierregister;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.Result;
import java.sql.SQLException;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.abstractions.IValidator;
import com.webapi.application.validators.EmailValidator;
import com.webapi.application.validators.NotEmptyStringValidator;
import com.webapi.application.validators.PasswordValidator;
import com.webapi.application.validators.PhoneNumberValidator;
import com.webapi.application.validators.StringsMustBeEqualValidator;
import java.util.ArrayList;
import java.util.List;

public class SupplierRegisterRequestHandler implements IRequestHandler<SupplierRegisterRequest, Void> {

    private static final String PASSWORD_VERIFICATION_KEY = "passwordVerification";
    private static final String PASSWORD_VERIFICATION_EMPTY_MESSAGE = "Şifre tekrarı boş bırakılamaz.";
    private static final String PASSWORDS_NOT_EQUAL_MESSAGE = "Şifreler eşleşmiyor.";
    private static final String NAME_KEY = "name";
    private static final String NAME_EMPTY_MESSAGE = "İsim boş bırakılamaz.";
    private static final String EMAIL_KEY = "email";

    @Override
    public IResult<Void> handle(SupplierRegisterRequest request) throws SQLException {
        IResult<Void> result = new Result<>();

        List<IValidator> validators = new ArrayList<>();
        validators.add(new EmailValidator(request.getEmail()));
        validators.add(new PasswordValidator(request.getPassword()));
        validators.add(new NotEmptyStringValidator(NAME_KEY, request.getName(), NAME_EMPTY_MESSAGE));
        validators.add(new PhoneNumberValidator(request.getPhoneNumber()));
        validators.add(new NotEmptyStringValidator(PASSWORD_VERIFICATION_KEY, request.getPasswordVerification(), PASSWORD_VERIFICATION_EMPTY_MESSAGE));
        validators.add(new StringsMustBeEqualValidator(PASSWORD_VERIFICATION_KEY, request.getPasswordVerification(), request.getPassword(), PASSWORDS_NOT_EQUAL_MESSAGE));

        for (IValidator validator : validators) {
            validator.validate(result);
        }

        if (result.isSucceeded()) {
            ISQLOperation<String, Boolean> supplierExistsQuery = new SupplierExistsQuery();

            if (!supplierExistsQuery.execute(request.getEmail())) {
                ISQLOperation<SupplierRegisterRequest, Boolean> registerSuppliercommand = new SupplierRegisterCommand();

                if (!registerSuppliercommand.execute(request)) {
                    result.addError("unknown", "Bilinmeyen bir hatadan dolayı kullanıcı oluşturulamadı.");
                }
            } else {
                result.addError(EMAIL_KEY, "Bu email adresi ile ilişkili bir satıcı bulunmaktadır.");
            }
        }

        return result;
    }

}
