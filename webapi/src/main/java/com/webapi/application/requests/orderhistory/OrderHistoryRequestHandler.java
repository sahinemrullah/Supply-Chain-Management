
package com.webapi.application.requests.orderhistory;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.concretes.Result;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;

public class OrderHistoryRequestHandler implements IRequestHandler<OrderHistoryRequest, PaginatedListModel<OrderHistoryModel>> {

    @Override
    public IResult<PaginatedListModel<OrderHistoryModel>> handle(OrderHistoryRequest request) throws SQLException {
        IResult<PaginatedListModel<OrderHistoryModel>> result = new Result<>();
        
        ISQLOperation<OrderHistoryRequest, PaginatedListModel<OrderHistoryModel>> orderHistoryQuery = new OrderHistoryQuery();
        
        PaginatedListModel<OrderHistoryModel> orders = orderHistoryQuery.execute(request);
        
        result.setItem(orders);
        
        return result;
    }

}
