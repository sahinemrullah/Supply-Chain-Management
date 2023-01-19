
package com.webapi.application.requests.supplierproducts;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;

public class SupplierProductsRequestHandler implements IRequestHandler<SupplierProductsRequest, PaginatedListModel<SupplierProductsListModel>> {

    @Override
    public IResult<PaginatedListModel<SupplierProductsListModel>> handle(SupplierProductsRequest request) throws SQLException {
        return ResultBuilder
                .createPaginated(request, SupplierProductsListModel.class)
                .withQuery(new SupplierProductsQuery())
                .build();
    }

}
