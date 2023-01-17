package com.webapi.application.requests.confirmorder;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.Result;
import java.sql.SQLException;
import com.webapi.application.abstractions.ISQLOperation;

public class ConfirmOrderRequestHandler implements IRequestHandler<ConfirmOrderRequest, Void> {

    @Override
    public IResult<Void> handle(ConfirmOrderRequest request) {
        IResult<Void> result = new Result<>();
        
        ISQLOperation<ConfirmOrderRequest, String> command = new ConfirmOrderRequestCommand();
        
        String errorMessage;
        
        try {
            errorMessage = command.execute(request);
        } catch (SQLException ex) {
            result.addError("exception", ex.getMessage());
            return result;
        }
        
        if(errorMessage != null)
            result.addError("product", errorMessage);
        
        return result;
    }

}
