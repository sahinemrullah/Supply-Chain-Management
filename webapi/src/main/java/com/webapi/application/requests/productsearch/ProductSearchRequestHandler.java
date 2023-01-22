package com.webapi.application.requests.productsearch;

import com.webapi.persistence.queries.ProductSearchQuery;
import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;

public class ProductSearchRequestHandler implements IRequestHandler<ProductSearchRequest, PaginatedListModel<ProductSearchModel>> {

    @Override
    public IResult<PaginatedListModel<ProductSearchModel>> handle(ProductSearchRequest request) throws SQLException {
        return ResultBuilder
                .createPaginated(request, ProductSearchModel.class)
                .withQuery(new ProductSearchQuery())
                .build();
    }

}
