package com.webapi.application.requests.supplierlogin;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.Result;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.models.AccessToken;
import com.webapi.application.models.AccessTokenFactory;
import java.sql.SQLException;

public class SupplierLoginRequestHandler implements IRequestHandler<SupplierLoginRequest, AccessToken> {

    private static final String EMAIL_KEY = "email";
    private static final String INVALID_CREDENTIALS_ERROR = "Geçersiz kullanıcı bilgileri girdiniz.";

    @Override
    public IResult<AccessToken> handle(SupplierLoginRequest request) throws SQLException {
        IResult<AccessToken> result = new Result<>();

        ISQLOperation<SupplierLoginRequest, AccessTokenFactory> command = new SupplierLoginCommand();

        AccessTokenFactory factory = command.execute(request);
        AccessToken accessToken = factory.build();
        if (accessToken == null) {
            result.addError(EMAIL_KEY, INVALID_CREDENTIALS_ERROR);
        } else {
            result.setItem(accessToken);
        }

        return result;
    }

}
