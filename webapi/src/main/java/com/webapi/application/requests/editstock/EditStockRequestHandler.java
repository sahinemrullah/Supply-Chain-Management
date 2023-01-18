package com.webapi.application.requests.editstock;

import com.webapi.application.requests.editdiscount.*;
import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.concretes.Result;
import com.webapi.application.exceptions.NotFoundException;
import java.sql.SQLException;

public class EditStockRequestHandler implements IRequestHandler<EditStockRequest, Void> {
    private static final String UNKNOWN_ERROR_KEY = "";
    private static final String UNKNOWN_ERROR_MESSAGE = "Bilinmeyen bir hata oluştu lütfen daha sonra tekrar deneyiniz.";
    private static final String NOT_FOUND_ERROR_KEY = "product";
    @Override
    public IResult<Void> handle(EditStockRequest request) throws SQLException {
        ISQLOperation<Integer, Boolean> productExistsQuery = new ProductExistsQuery();
        IResult<Void> result = new Result<>();
        if (productExistsQuery.execute(request.getId())) {
            ISQLOperation<EditStockRequest, Boolean> editStockCommand = new EditStockCommand();
            if (!editStockCommand.execute(request)) {
                result.addError(UNKNOWN_ERROR_KEY, UNKNOWN_ERROR_MESSAGE);
            }
        } else {
            throw new NotFoundException(NOT_FOUND_ERROR_KEY, String.valueOf(request.getId()));
        }
        return result;
    }

}
