
package com.webapi.application.requests.orderdetails;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import java.sql.SQLException;

public class OrderDetailsRequestHandler implements IRequestHandler<OrderDetailsRequest, OrderDetailsModel> {

    @Override
    public IResult<OrderDetailsModel> handle(OrderDetailsRequest request) throws SQLException {
        return ResultBuilder
                .create(request, OrderDetailsModel.class)
                .withQuery(new GetOrderDetailsQuery())
                .build();
    }

}
