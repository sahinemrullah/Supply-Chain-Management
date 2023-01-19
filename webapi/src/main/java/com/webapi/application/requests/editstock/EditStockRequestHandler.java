package com.webapi.application.requests.editstock;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.exceptions.NotFoundException;
import com.webapi.application.requests.editdiscount.ProductExistsQuery;
import java.sql.SQLException;

public class EditStockRequestHandler implements IRequestHandler<EditStockRequest, Void> {

    private static final String UNKNOWN_ERROR_MESSAGE = "Bilinmeyen bir hata oluştu lütfen daha sonra tekrar deneyiniz.";
    private static final String NOT_FOUND_ERROR_KEY = "product";

    @Override
    public IResult<Void> handle(EditStockRequest request) throws SQLException {
        return ResultBuilder
                .create(request, Void.class)
                .check(request.getId(), new ProductExistsQuery())
                    .withException(new NotFoundException(NOT_FOUND_ERROR_KEY, String.valueOf(request.getId())))
                .check(new EditStockCommand())
                    .withError("", UNKNOWN_ERROR_MESSAGE)
                .build();
    }

}
