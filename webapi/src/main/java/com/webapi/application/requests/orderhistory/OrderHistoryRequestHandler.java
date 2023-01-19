package com.webapi.application.requests.orderhistory;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;

public class OrderHistoryRequestHandler implements IRequestHandler<OrderHistoryRequest, PaginatedListModel<OrderHistoryModel>> {

    @Override
    public IResult<PaginatedListModel<OrderHistoryModel>> handle(OrderHistoryRequest request) throws SQLException {

        return ResultBuilder
                .createPaginated(request, OrderHistoryModel.class)
                .withQuery(new OrderHistoryQuery())
                .build();
    }

}
