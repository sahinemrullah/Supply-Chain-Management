
package com.webapi.application.requests.retailerproducts;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.concretes.Result;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;

public class RetailerProductsRequestHandler implements IRequestHandler<RetailerProductsRequest, PaginatedListModel<RetailerProductsListModel>> {

    @Override
    public IResult<PaginatedListModel<RetailerProductsListModel>> handle(RetailerProductsRequest request) throws SQLException {
        IResult<PaginatedListModel<RetailerProductsListModel>> result = new Result<>();
        
        ISQLOperation<RetailerProductsRequest, PaginatedListModel<RetailerProductsListModel>> getRetailerProductsQuery = new RetailerProductsQuery();
        
        PaginatedListModel<RetailerProductsListModel> products = getRetailerProductsQuery.execute(request);
        
        result.setItem(products);
        
        return result;
    }

}
