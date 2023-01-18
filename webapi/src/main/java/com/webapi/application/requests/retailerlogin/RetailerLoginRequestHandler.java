package com.webapi.application.requests.retailerlogin;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.Result;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.models.AccessToken;
import com.webapi.application.models.AccessTokenFactory;
import java.sql.SQLException;

public class RetailerLoginRequestHandler implements IRequestHandler<RetailerLoginRequest, AccessToken> {

    private static final String EMAIL_KEY = "email";
    private static final String INVALID_CREDENTIALS_ERROR = "Geçersiz kullanıcı bilgileri girdiniz.";

    @Override
    public IResult<AccessToken> handle(RetailerLoginRequest request) throws SQLException {
        IResult<AccessToken> result = new Result<>();

        ISQLOperation<RetailerLoginRequest, AccessTokenFactory> command = new RetailerLoginCommand();

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
