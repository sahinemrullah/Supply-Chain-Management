package com.webapi.application.requests.retailerlogin;

import com.webapi.persistence.queries.RetailerLoginQuery;
import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.persistence.abstractions.ISQLOperation;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.models.AccessToken;
import com.webapi.application.models.AccessTokenFactory;
import java.sql.SQLException;

public class RetailerLoginRequestHandler implements IRequestHandler<RetailerLoginRequest, AccessToken> {

    private static final String EMAIL_KEY = "email";
    private static final String INVALID_CREDENTIALS_ERROR = "Geçersiz kullanıcı bilgileri girdiniz.";

    @Override
    public IResult<AccessToken> handle(RetailerLoginRequest request) throws SQLException {

        ISQLOperation<RetailerLoginRequest, AccessTokenFactory> command = new RetailerLoginQuery();

        AccessTokenFactory factory = command.execute(request);
        AccessToken accessToken = factory.build();
        
        return ResultBuilder
                .create(request, AccessToken.class)
                .check(accessToken != null)
                    .withException(new ModelValidationException(EMAIL_KEY, INVALID_CREDENTIALS_ERROR))
                .withItem(accessToken)
                .build();
    }

}
