
package com.webapi.application.requests.productsearch;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.concretes.Result;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;

public class ProductSearchRequestHandler implements IRequestHandler<ProductSearchRequest, PaginatedListModel<ProductSearchModel>> {

    @Override
    public IResult<PaginatedListModel<ProductSearchModel>> handle(ProductSearchRequest request) throws SQLException {
        IResult<PaginatedListModel<ProductSearchModel>> result = new Result<>();
        
        ISQLOperation<ProductSearchRequest, PaginatedListModel<ProductSearchModel>> productSearchQuery = new ProductSearchQuery();
        
        PaginatedListModel<ProductSearchModel> products = productSearchQuery.execute(request);
        
        result.setItem(products);
        
        return result;
    }

}
