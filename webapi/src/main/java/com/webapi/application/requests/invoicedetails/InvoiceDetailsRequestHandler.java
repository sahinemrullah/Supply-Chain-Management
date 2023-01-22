
package com.webapi.application.requests.invoicedetails;

import com.webapi.persistence.queries.GetInvoiceQuery;
import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import java.sql.SQLException;

public class InvoiceDetailsRequestHandler implements IRequestHandler<InvoiceDetailsRequest, InvoiceDetailsModel> {

    @Override
    public IResult<InvoiceDetailsModel> handle(InvoiceDetailsRequest request) throws SQLException {
        return ResultBuilder.create(request, InvoiceDetailsModel.class)
                .withQuery(new GetInvoiceQuery())
                .build();
    }

}
