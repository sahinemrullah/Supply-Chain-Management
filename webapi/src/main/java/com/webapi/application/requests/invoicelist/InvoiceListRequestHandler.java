
package com.webapi.application.requests.invoicelist;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;

public class InvoiceListRequestHandler implements IRequestHandler<InvoiceListRequest, PaginatedListModel<InvoiceListModel>> {

    @Override
    public IResult<PaginatedListModel<InvoiceListModel>> handle(InvoiceListRequest request) throws SQLException {
        return ResultBuilder.createPaginated(request, InvoiceListModel.class)
                .withQuery(new GetInvoiceListQuery())
                .build();
    }

}
