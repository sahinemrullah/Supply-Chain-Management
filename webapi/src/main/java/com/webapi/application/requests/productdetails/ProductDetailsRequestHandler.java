
package com.webapi.application.requests.productdetails;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import java.sql.SQLException;

public class ProductDetailsRequestHandler implements IRequestHandler<ProductDetailsRequest, ProductDetailsModel> {

    @Override
    public IResult<ProductDetailsModel> handle(ProductDetailsRequest request) throws SQLException {
        return ResultBuilder
                .create(request, ProductDetailsModel.class)
                .withQuery(new ProductDetailsQuery())
                .build();
    }

}
