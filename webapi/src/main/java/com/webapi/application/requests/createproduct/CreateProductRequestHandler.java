package com.webapi.application.requests.createproduct;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.Result;
import java.sql.SQLException;
import com.webapi.application.abstractions.ISQLOperation;

public class CreateProductRequestHandler implements IRequestHandler<CreateProductRequest, Void> {

    @Override
    public IResult<Void> handle(CreateProductRequest request) throws SQLException {
        IResult<Void> result = new Result<>();

        ISQLOperation<CreateProductRequest, Boolean> command = new CreateProductCommand();

        if (!command.execute(request)) {
            result.addError("", "Bilinmeyen bir hatadan dolayı ürün oluşturulamadı.");
        }

        return result;
    }

}
