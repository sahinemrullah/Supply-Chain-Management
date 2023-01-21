
package com.webapi.application.requests.pendingorders;

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

public class GetPendingOrdersQuery extends PaginatedSQLQuery<PendingOrderRequest, PendingOrderListModel> {
    
    private static final String QUERY = "SELECT SQL_CALC_FOUND_ROWS od.order_m_id, SUM(p.price * odd.quantity) AS price, r.name, om.created_date, odd.order_m_d_id " +
                                        "FROM order_m_d AS od " +
                                        "JOIN order_m AS om ON om.order_m_id = od.order_m_id " +
                                        "JOIN retailer AS r ON r.retailer_id = om.retailer_id " +
                                        "JOIN order_m_d_d AS odd ON od.order_m_d_id = odd.order_m_d_id " +
                                        "JOIN product AS p ON odd.product_id = p.product_id " +
                                        "LEFT JOIN invoiceitem AS it ON it.order_m_d_d_id = odd.order_m_d_d_id " +
                                        "WHERE od.supplier_id = ? AND it.invoice_item_id IS NULL " +
                                        "GROUP BY odd.order_m_d_id " +
                                        "LIMIT ? OFFSET ?";
    
    @Override
    public PaginatedListModel<PendingOrderListModel> execute(PendingOrderRequest params) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        
        ArrayList<PendingOrderListModel> orders;
        try (PreparedStatement statement = con.prepareStatement(QUERY)) {
            statement.setInt(1, params.getSupplierId());
            statement.setInt(2, params.getPageSize());
            statement.setInt(3, (params.getPageNumber() - 1) * params.getPageSize());
            try (ResultSet result = statement.executeQuery()) {
                orders = new ArrayList<>();
                while(result.next()) {
                    PendingOrderListModel order = new PendingOrderListModel();
                    order.setOrderId(result.getInt("order_m_id"));
                    order.setSupplierName(result.getString("name"));
                    order.setPrice(result.getDouble("price"));
                    Timestamp ts1 = result.getTimestamp("created_date");
                    order.setCreatedDate(new Date(ts1.getTime()));
                    orders.add(order);
                }
            }
        }
        
        return paginatedQueryEnd(con, params.getPageNumber(), params.getPageSize(), orders);
    }

}
