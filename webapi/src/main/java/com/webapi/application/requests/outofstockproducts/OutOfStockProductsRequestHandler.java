
package com.webapi.application.requests.outofstockproducts;

import com.webapi.persistence.queries.OutOfStockProductsQuery;
import com.webapi.application.requests.supplierproducts.SupplierProductsListModel;
import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;

public class OutOfStockProductsRequestHandler implements IRequestHandler<OutOfStockProductsRequest, PaginatedListModel<SupplierProductsListModel>> {

    @Override
    public IResult<PaginatedListModel<SupplierProductsListModel>> handle(OutOfStockProductsRequest request) throws SQLException {
        return ResultBuilder
                .createPaginated(request, SupplierProductsListModel.class)
                .withQuery(new OutOfStockProductsQuery())
                .build();
    }

}
