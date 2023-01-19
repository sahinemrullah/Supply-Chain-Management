package com.webapi.application.requests.createproduct;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import java.sql.SQLException;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.concretes.ResultBuilder;

public class CreateProductRequestHandler implements IRequestHandler<CreateProductRequest, Void> {

    @Override
    public IResult<Void> handle(CreateProductRequest request) throws SQLException {
        ISQLOperation<CreateProductRequest, Boolean> command = new CreateProductCommand();

        return ResultBuilder
                .create(request, Void.class)
                .check(command.execute(request))
                    .withError("", "Bilinmeyen bir hatadan dolayı ürün oluşturulamadı.")
                .build();
    }

}
