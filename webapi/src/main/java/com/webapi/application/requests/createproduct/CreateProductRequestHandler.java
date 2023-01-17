package com.webapi.application.requests.createproduct;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.Result;
import java.sql.SQLException;
import com.webapi.application.abstractions.ISQLOperation;

public class CreateProductRequestHandler implements IRequestHandler<CreateProductRequest, Void> {

    @Override
    public IResult<Void> handle(CreateProductRequest request) {
        IResult<Void> result = new Result<>();

        ISQLOperation<CreateProductRequest, Boolean> command = new CreateProductCommand();

        try {
            if (!command.execute(request)) {
                result.addError("", "Bilinmeyen bir hatadan dolayı ürün oluşturulamadı.");
            }
        } catch (SQLException ex) {
            result.addError("exception", ex.getMessage());
            return result;
        }

        return result;
    }

}
