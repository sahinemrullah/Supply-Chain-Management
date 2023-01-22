package com.webapi.application.requests.confirmorder;

import com.webapi.persistence.commands.ConfirmOrderRequestCommand;
import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import java.sql.SQLException;
import com.webapi.persistence.abstractions.ISQLOperation;
import com.webapi.application.concretes.ResultBuilder;

public class ConfirmOrderRequestHandler implements IRequestHandler<ConfirmOrderRequest, Void> {

    @Override
    public IResult<Void> handle(ConfirmOrderRequest request) throws SQLException {
        ISQLOperation<ConfirmOrderRequest, String> command = new ConfirmOrderRequestCommand();

        String errorMessage = command.execute(request);
        
        return ResultBuilder
                .create(request, Void.class)
                .check(errorMessage == null)
                    .withError("", errorMessage)
                .build();
    }

}
