package com.webapi.application.requests.invoicedetails;

import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.exceptions.NotFoundException;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetInvoiceQuery implements ISQLOperation<InvoiceDetailsRequest, InvoiceDetailsModel> {

    private static final String QUERY = "SELECT i.invoice_id, i.created_date, r.name AS retailer_name, s.name AS supplier_name, p.name AS product_name, p.price, odd.quantity, odd.discount, p.path "
            + "FROM invoice AS i "
            + "JOIN supplier AS s ON i.supplier_id = s.supplier_id "
            + "JOIN retailer AS r ON i.retailer_id = r.retailer_id "
            + "JOIN invoiceitem AS it ON i.invoice_id = it.invoice_id "
            + "JOIN order_m_d_d AS odd ON it.order_m_d_d_id = odd.order_m_d_d_id "
            + "JOIN (SELECT p.product_id, p.name, p.price, MIN(pi.path) AS path  " +
            "		FROM product as p " +
            "		LEFT JOIN productimage AS pi on p.product_id = pi.product_id "
            + "         GROUP BY pi.product_id, p.product_id " +
            "        ) AS p ON p.product_id = odd.product_id "
            + "WHERE i.%s_id = ? AND i.invoice_id = ?";
    private static final int USER_INDEX = 1;
    private static final int INVOICE_INDEX = 2;

    @Override
    public InvoiceDetailsModel execute(InvoiceDetailsRequest params) throws SQLException {
        InvoiceDetailsModel model = null;
        try (Connection con = DatabaseConnection.getConntection(); PreparedStatement statement = con.prepareStatement(String.format(QUERY, params.getRole()))) {
            statement.setInt(USER_INDEX, params.getUserId());
            statement.setInt(INVOICE_INDEX, params.getId());
            
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                model = new InvoiceDetailsModel();
                model.setId(result.getInt("invoice_id"));
                Timestamp ts1 = result.getTimestamp("created_date");
                model.setCreatedDate(new Date(ts1.getTime()));
                model.setRetailer(result.getString("retailer_name"));
                model.setSupplier(result.getString("supplier_name"));

                List<InvoiceItemModel> items = new ArrayList<>();

                items.add(parseItem(result));

                while (result.next()) {
                    items.add(parseItem(result));
                }
                
                model.setItems(items);
            } else {
                throw new NotFoundException("fatura", String.valueOf(params.getId()));
            }
        }
        return model;
    }

    private static InvoiceItemModel parseItem(ResultSet result) throws SQLException {

        InvoiceItemModel item = new InvoiceItemModel();

        item.setName(result.getString("product_name"));
        item.setPrice(result.getDouble("price"));
        item.setQuantity(result.getInt("quantity"));
        item.setDiscount(result.getDouble("discount"));
        item.setImagePath(result.getString("path"));

        return item;
    }
}
