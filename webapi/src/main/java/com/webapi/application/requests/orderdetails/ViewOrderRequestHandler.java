
package com.webapi.application.requests.orderdetails;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import java.sql.SQLException;

public class ViewOrderRequestHandler implements IRequestHandler<ViewOrderRequest, ViewOrderModel> {

    @Override
    public IResult<ViewOrderModel> handle(ViewOrderRequest request) throws SQLException {
        return ResultBuilder
                .create(request, ViewOrderModel.class)
                .withItem(new ViewOrderQuery())
                .build();
    }

}
