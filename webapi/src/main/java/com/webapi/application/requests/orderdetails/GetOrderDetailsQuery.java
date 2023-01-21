
package com.webapi.application.requests.orderdetails;

import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class GetOrderDetailsQuery implements ISQLOperation<OrderDetailsRequest, OrderDetailsModel> {

    @Override
    public OrderDetailsModel execute(OrderDetailsRequest params) throws SQLException {
        OrderDetailsModel model;
        try (Connection con = DatabaseConnection.getConntection()) {
            PreparedStatement statement;
            if(params.getRole().equalsIgnoreCase("retailer"))
                statement = findByIdRetailer(con, params.getId(), params.getUserId());
            else
                statement = findByIdSupplier(con, params.getId(), params.getUserId());
            ResultSet result = statement.executeQuery();
            model = null;
            if(result.next()) {
                model = new OrderDetailsModel();
                
                Timestamp ts1 = result.getTimestamp("created_date");
                model.setCreatedDate(new Date(ts1.getTime()));
                
                model.addProduct(getViewOrderProduct(result));
                
                while(result.next()) {
                    model.addProduct(getViewOrderProduct(result));
                }
            }
        }
        
        return model;
    }
    
    
    private static OrderDetailsListModel getViewOrderProduct(ResultSet result) throws SQLException {
        OrderDetailsListModel product = new OrderDetailsListModel();
        
        product.setId(result.getInt("product_id"));
        product.setName(result.getString("name"));
        product.setStock(result.getInt("stock"));
        product.setQuantity(result.getInt("quantity"));
        product.setPrice(result.getDouble("price"));
        product.setDiscount(result.getDouble("discount"));
        product.setUserName(result.getString("userName"));
        
        return product;
    }
    
    private static PreparedStatement findByIdRetailer(Connection con, int id, int retailerId) throws SQLException {
        PreparedStatement statement = con.prepareStatement("SELECT odd.quantity, p.product_id, p.name, p.stock, p.price, s.name AS userName, odd.discount, om.created_date " + 
                                                            "FROM order_m_d AS od " +
                                                            "JOIN order_m AS om ON om.order_m_id = od.order_m_id " +
                                                            "JOIN order_m_d_d AS odd ON od.order_m_d_id = odd.order_m_d_id " +
                                                            "JOIN product AS p ON odd.product_id = p.product_id " +
                                                            "JOIN supplier AS s ON s.supplier_id = od.supplier_id " +
                                                            "WHERE om.order_m_id = ? AND om.retailer_id = ?");
        
        statement.setInt(1, id);
        statement.setInt(2, retailerId);
        
        return statement;
    }
    
    private static PreparedStatement findByIdSupplier(Connection con, int id, int supplierId) throws SQLException {
        PreparedStatement statement = con.prepareStatement("SELECT odd.quantity, p.product_id, p.name, p.stock, p.price, r.name AS userName, odd.discount, om.created_date " + 
                                                            "FROM order_m_d AS od " +
                                                            "JOIN order_m AS om ON om.order_m_id = od.order_m_id " +
                                                            "JOIN order_m_d_d AS odd ON od.order_m_d_id = odd.order_m_d_id " +
                                                            "JOIN product AS p ON odd.product_id = p.product_id " +
                                                            "JOIN retailer AS r ON r.retailer_id = om.retailer_id " +
                                                            "WHERE om.order_m_id = ? AND od.supplier_id = ?");
        
        statement.setInt(1, id);
        statement.setInt(2, supplierId);
        
        return statement;
    }

}
