
package com.webapi.application.requests.retailerproducts;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;

public class RetailerProductsRequestHandler implements IRequestHandler<RetailerProductsRequest, PaginatedListModel<RetailerProductsListModel>> {

    @Override
    public IResult<PaginatedListModel<RetailerProductsListModel>> handle(RetailerProductsRequest request) throws SQLException {
        return ResultBuilder
                .createPaginated(request, RetailerProductsListModel.class)
                .withItem(new RetailerProductsQuery())
                .build();
    }

}
