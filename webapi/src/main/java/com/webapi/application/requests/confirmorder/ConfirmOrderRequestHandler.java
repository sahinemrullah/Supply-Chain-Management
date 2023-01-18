package com.webapi.application.requests.confirmorder;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.Result;
import java.sql.SQLException;
import com.webapi.application.abstractions.ISQLOperation;

public class ConfirmOrderRequestHandler implements IRequestHandler<ConfirmOrderRequest, Void> {

    @Override
    public IResult<Void> handle(ConfirmOrderRequest request) throws SQLException {
        IResult<Void> result = new Result<>();

        ISQLOperation<ConfirmOrderRequest, String> command = new ConfirmOrderRequestCommand();

        String errorMessage;

        errorMessage = command.execute(request);

        if (errorMessage != null) {
            result.addError("product", errorMessage);
        }

        return result;
    }

}
