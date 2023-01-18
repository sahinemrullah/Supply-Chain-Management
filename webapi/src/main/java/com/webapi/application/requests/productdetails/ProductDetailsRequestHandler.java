
package com.webapi.application.requests.productdetails;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.concretes.Result;
import java.sql.SQLException;

public class ProductDetailsRequestHandler implements IRequestHandler<ProductDetailsRequest, ProductDetailsModel> {

    @Override
    public IResult<ProductDetailsModel> handle(ProductDetailsRequest request) throws SQLException {
        IResult<ProductDetailsModel> result = new Result<>();
        
        ISQLOperation<ProductDetailsRequest, ProductDetailsModel> findByIdProductQuery = new ProductDetailsQuery();
        
        ProductDetailsModel model = findByIdProductQuery.execute(request);
        
        result.setItem(model);
        
        return result;
    }

}
