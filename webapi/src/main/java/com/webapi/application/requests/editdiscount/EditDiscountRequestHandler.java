package com.webapi.application.requests.editdiscount;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.exceptions.NotFoundException;
import java.sql.SQLException;

public class EditDiscountRequestHandler implements IRequestHandler<EditDiscountRequest, Void> {

    private static final String UNKNOWN_ERROR_MESSAGE = "Bilinmeyen bir hata oluştu lütfen daha sonra tekrar deneyiniz.";
    private static final String NOT_FOUND_ERROR_KEY = "product";

    @Override
    public IResult<Void> handle(EditDiscountRequest request) throws SQLException {
        return ResultBuilder
                .create(request, Void.class)
                .check(request.getId(), new ProductExistsQuery())
                    .withException(new NotFoundException(NOT_FOUND_ERROR_KEY, String.valueOf(request.getId())))
                .check(new EditDiscountCommand())
                    .withError("", UNKNOWN_ERROR_MESSAGE)
                .build();
    }

}
