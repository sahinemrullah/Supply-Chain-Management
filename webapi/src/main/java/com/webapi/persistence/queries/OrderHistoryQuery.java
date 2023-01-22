
package com.webapi.persistence.queries;

import com.webapi.persistence.concretes.PaginatedSQLQuery;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.requests.orderhistory.OrderHistoryModel;
import com.webapi.application.requests.orderhistory.OrderHistoryRequest;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class OrderHistoryQuery extends PaginatedSQLQuery<OrderHistoryRequest, OrderHistoryModel> {
    private static final String QUERY = "SELECT SQL_CALC_FOUND_ROWS od.order_m_id, s.name, SUM(p.price * (1 - odd.discount) * odd.quantity) AS price, om.created_date, i.invoice_id, i.invoice_id IS NULL AS is_pending " +
                                                            "FROM order_m_d AS od " +
                                                            "JOIN order_m AS om ON om.order_m_id = od.order_m_id " +
                                                            "JOIN supplier AS s ON s.supplier_id = od.supplier_id " +
                                                            "JOIN order_m_d_d AS odd ON od.order_m_d_id = odd.order_m_d_id " +
                                                            "JOIN product AS p ON odd.product_id = p.product_id " +
                                                            "LEFT JOIN invoiceitem as i ON i.order_m_d_d_id = odd.order_m_d_d_id " +
                                                            "WHERE om.retailer_id = ? " +
                                                            "GROUP BY odd.order_m_d_id, i.invoice_id " +
                                                            "ORDER BY odd.order_m_d_id DESC " +
                                                            "LIMIT ? OFFSET ?";
    @Override
    public PaginatedListModel<OrderHistoryModel> execute(OrderHistoryRequest params) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        
        PreparedStatement statement = con.prepareStatement(QUERY);
        
        statement.setInt(1, params.getRetailerId());
        statement.setInt(2, params.getPageSize());
        statement.setInt(3, (params.getPageNumber() - 1) * params.getPageSize());
        
        ResultSet result = statement.executeQuery();
        
        ArrayList<OrderHistoryModel> orders = new ArrayList<>();
        
        while(result.next()) {
            OrderHistoryModel order = new OrderHistoryModel();
            order.setId(result.getInt("order_m_id"));
            order.setTotal(result.getDouble("price"));
            order.setSupplierName(result.getString("name"));
            Timestamp ts1 = result.getTimestamp("created_date");
            order.setCreatedDate(new Date(ts1.getTime()));
            order.setInvoiceId(result.getInt("invoice_id"));
            order.setIsPending(result.getBoolean("is_pending"));
            orders.add(order);
        }
        
        result.close();

        return paginatedQueryEnd(con, params.getPageNumber(), params.getPageSize(), orders);
    }
    

}
