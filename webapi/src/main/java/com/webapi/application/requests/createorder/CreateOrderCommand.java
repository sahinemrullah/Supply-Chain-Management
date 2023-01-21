
package com.webapi.application.requests.createorder;

import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class CreateOrderCommand implements ISQLOperation<CreateOrderRequest, Integer> {
    private Map<Integer, List<CreateOrderProductModel>> productsBySupplier;

    public CreateOrderCommand(Map<Integer, List<CreateOrderProductModel>> productsByRetailer) {
        this.productsBySupplier = productsByRetailer;
    }
    
    @Override
    public Integer execute(CreateOrderRequest params) throws SQLException {
        try (Connection con = DatabaseConnection.getConntection()) {
            con.setAutoCommit(false);
            
            PreparedStatement statement = con.prepareStatement("INSERT INTO order_m(retailer_id, created_date) VALUES(?,CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, params.getRetailerId());
            int rowCount = statement.executeUpdate();
            
            if(rowCount == 1) {
                ResultSet result = statement.getGeneratedKeys();
                
                if(result.next()) {
                    int orderId = result.getInt(1);
                    for(Map.Entry<Integer, List<CreateOrderProductModel>> supplier : productsBySupplier.entrySet()) {
                        insertOrderMasterDetail(orderId, con, supplier, params.getProducts());
                    }
                    con.commit();
                    con.close();
                    return orderId;
                }
            }
        }
        return Statement.EXECUTE_FAILED;
    }
    
    private static void insertOrderMasterDetail(int id, Connection con, Map.Entry<Integer, List<CreateOrderProductModel>> supplier, Map<Integer, Integer> quantities) throws SQLException {
        PreparedStatement statement = con.prepareStatement("INSERT INTO order_m_d(order_m_id, supplier_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
        
        statement.setInt(1, id);
        statement.setInt(2, supplier.getKey());
        
        int rowCount = statement.executeUpdate();
        
        if(rowCount == 1) {
            ResultSet result = statement.getGeneratedKeys();
            
            if(result.next()) {
                int orderMasterDetailId = result.getInt(1);
                insertOrderMasterDetailDetail(con, orderMasterDetailId, supplier.getValue(), quantities);
            }
        }
    }
    
    private static void insertOrderMasterDetailDetail(Connection con, int id, List<CreateOrderProductModel> products, Map<Integer, Integer> quantities) throws SQLException {
        PreparedStatement statement = con.prepareStatement("INSERT INTO order_m_d_d(order_m_d_id, product_id, quantity, discount) VALUES(?,?,?,?)");
        
        for(CreateOrderProductModel product : products) {
            statement.setInt(1, id);
            statement.setInt(2, product.getId());
            statement.setInt(3, quantities.get(product.getId()));
            statement.setDouble(4, product.getDiscount());
            statement.addBatch();
        }
        
        statement.executeBatch();
    }
}
