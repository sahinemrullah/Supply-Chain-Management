package com.webapi.application.requests.invoicelist;

import com.webapi.application.concretes.PaginatedSQLQuery;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetInvoiceListQuery extends PaginatedSQLQuery<InvoiceListRequest, InvoiceListModel> {

    private static final String QUERY = "SELECT i.created_date, i.invoice_id FROM invoice AS i WHERE (i.%s_id = ?)";
    private static final int USER_INDEX = 1;

    @Override
    public PaginatedListModel<InvoiceListModel> execute(InvoiceListRequest params) throws SQLException {
        PaginatedListModel<InvoiceListModel> model = new PaginatedListModel<>();

        try (Connection con = DatabaseConnection.getConntection(); PreparedStatement statement = con.prepareStatement(String.format(QUERY, params.getRole()))) {

            statement.setInt(USER_INDEX, params.getUserId());

            ResultSet result = statement.executeQuery();

            List<InvoiceListModel> items = new ArrayList<>();

            while (result.next()) {
                InvoiceListModel listModel = new InvoiceListModel();

                listModel.setId(result.getInt("invoice_id"));
                Timestamp ts1 = result.getTimestamp("created_date");
                listModel.setCreatedDate(new Date(ts1.getTime()));
                items.add(listModel);
            }

            model.setItems(items);
        }
        return model;
    }

}
