
package com.webapi.application.requests.outofstockproducts;

import com.webapi.application.requests.retailerproducts.*;
import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;

public class OutOfStockProductsRequestHandler implements IRequestHandler<OutOfProductsRequest, PaginatedListModel<RetailerProductsListModel>> {

    @Override
    public IResult<PaginatedListModel<RetailerProductsListModel>> handle(OutOfProductsRequest request) throws SQLException {
        return ResultBuilder
                .createPaginated(request, RetailerProductsListModel.class)
                .withItem(new OutOfStockProductsQuery())
                .build();
    }

}
