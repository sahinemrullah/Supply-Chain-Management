
package com.webapi.application.requests.pendingorders;

import com.webapi.persistence.queries.GetPendingOrdersQuery;
import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;

public class PendingOrderRequestHandler implements IRequestHandler<PendingOrderRequest, PaginatedListModel<PendingOrderListModel>> {

    @Override
    public IResult<PaginatedListModel<PendingOrderListModel>> handle(PendingOrderRequest request) throws SQLException {
        return ResultBuilder
                .createPaginated(request, PendingOrderListModel.class)
                .withQuery(new GetPendingOrdersQuery())
                .build();
    }

}
