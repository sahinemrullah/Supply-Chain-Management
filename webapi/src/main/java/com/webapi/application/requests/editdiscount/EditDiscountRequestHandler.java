package com.webapi.application.requests.editdiscount;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.concretes.Result;
import com.webapi.application.exceptions.NotFoundException;
import java.sql.SQLException;

public class EditDiscountRequestHandler implements IRequestHandler<EditDiscountRequest, Void> {
    private static final String UNKNOWN_ERROR_KEY = "";
    private static final String UNKNOWN_ERROR_MESSAGE = "Bilinmeyen bir hata oluştu lütfen daha sonra tekrar deneyiniz.";
    private static final String NOT_FOUND_ERROR_KEY = "product";
    @Override
    public IResult<Void> handle(EditDiscountRequest request) throws SQLException {
        ISQLOperation<Integer, Boolean> productExistsQuery = new ProductExistsQuery();
        IResult<Void> result = new Result<>();
        if (productExistsQuery.execute(request.getId())) {
            ISQLOperation<EditDiscountRequest, Boolean> editDiscountCommand = new EditDiscountCommand();
            if (!editDiscountCommand.execute(request)) {
                result.addError(UNKNOWN_ERROR_KEY, UNKNOWN_ERROR_MESSAGE);
            }
        } else {
            throw new NotFoundException(NOT_FOUND_ERROR_KEY, String.valueOf(request.getId()));
        }
        return result;
    }

}
