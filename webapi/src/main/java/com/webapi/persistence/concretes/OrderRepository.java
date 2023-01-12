package com.webapi.persistence.concretes;

import com.webapi.persistence.abstractions.IOrderRepository;
import com.webapi.domain.entities.Order;
import com.webapi.domain.entities.Product;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OrderRepository implements IOrderRepository {

    @Override
    public boolean add(Order entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Order entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Order entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Order> getAll(Integer[] intgrs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int createOrder(int supplierId, List<Product> products, Map<Integer, Integer> quantities) throws SQLException {
        Map<Integer, List<Product>> productsByRetailer = products.stream()
                                                                 .collect(Collectors.groupingBy(p -> p.getRetailerId()));
        
        Connection con = DatabaseConnection.getConntection();
        con.setAutoCommit(false);
        
        PreparedStatement statement = con.prepareStatement("INSERT INTO order_m(supplier_id, created_date) VALUES(?,CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, supplierId);
        int rowCount = statement.executeUpdate();
        
        if(rowCount == 1) {
            ResultSet result = statement.getGeneratedKeys();
            
            if(result.next()) {
                int orderId = result.getInt(1);
                for(Entry<Integer, List<Product>> retailer : productsByRetailer.entrySet()) {
                    insertOrderMasterDetail(orderId, con, retailer, quantities);
                }
                con.commit();
                con.close();
                return orderId;
            }
        }
        con.close();
        return Statement.EXECUTE_FAILED;
    }
    
    private static void insertOrderMasterDetail(int id, Connection con, Entry<Integer, List<Product>> retailer, Map<Integer, Integer> quantities) throws SQLException {
        PreparedStatement statement = con.prepareStatement("INSERT INTO order_m_d(order_m_id, retailer_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
        
        statement.setInt(1, id);
        statement.setInt(2, retailer.getKey());
        
        int rowCount = statement.executeUpdate();
        
        if(rowCount == 1) {
            ResultSet result = statement.getGeneratedKeys();
            
            if(result.next()) {
                int orderMasterDetailId = result.getInt(1);
                insertOrderMasterDetailDetail(con, orderMasterDetailId, retailer.getValue(), quantities);
            }
        }
    }
    
    private static void insertOrderMasterDetailDetail(Connection con, int id, List<Product> products, Map<Integer, Integer> quantities) throws SQLException {
        PreparedStatement statement = con.prepareStatement("INSERT INTO order_m_d_d(order_m_d_id, product_id, quantity, discount) VALUES(?,?,?,?)");
        
        for(Product product : products) {
            statement.setInt(1, id);
            statement.setInt(2, product.getId());
            statement.setInt(3, quantities.get(product.getId()));
            statement.setDouble(4, 0);
            statement.addBatch();
        }
        
        statement.executeBatch();
    }
}
