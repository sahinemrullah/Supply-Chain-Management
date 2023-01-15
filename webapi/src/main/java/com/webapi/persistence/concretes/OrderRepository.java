package com.webapi.persistence.concretes;

import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.models.order.ViewOrderListModel;
import com.webapi.application.models.order.ViewOrderModel;
import com.webapi.application.models.order.PendingOrderModel;
import com.webapi.application.models.supplier.OrderHistoryModel;
import com.webapi.persistence.abstractions.IOrderRepository;
import com.webapi.domain.entities.Order;
import com.webapi.domain.entities.Product;
import com.webapi.persistence.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OrderRepository extends RepositoryBase<Order> implements IOrderRepository {

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
    public int createOrder(int supplierId, Map<Integer, List<Product>> productsByRetailer, Map<Integer, Integer> quantities) throws SQLException {
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

    @Override
    public PaginatedListModel<PendingOrderModel> getPendingOrders(int retailerId, int pageNumber, int pageSize) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        
        ArrayList<PendingOrderModel> orders;
        try (PreparedStatement statement = con.prepareStatement("SELECT SQL_CALC_FOUND_ROWS od.order_m_id, SUM(p.price) AS price, s.name, om.created_date, odd.order_m_d_id " +
                                                                "FROM order_m_d AS od " +
                                                                "JOIN order_m AS om ON om.order_m_id = od.order_m_id " +
                                                                "JOIN supplier AS s ON s.supplier_id = om.supplier_id " +
                                                                "JOIN order_m_d_d AS odd ON od.order_m_d_id = odd.order_m_d_id " +
                                                                "JOIN product AS p ON odd.product_id = p.product_id " +
                                                                "LEFT JOIN invoiceitem AS it ON it.order_m_d_d_id = odd.order_m_d_d_id " +
                                                                "WHERE od.retailer_id = ? AND it.invoice_item_id IS NULL " +
                                                                "GROUP BY odd.order_m_d_id " +
                                                                "LIMIT ? OFFSET ?")) {
            statement.setInt(1, retailerId);
            statement.setInt(2, pageSize);
            statement.setInt(3, (pageNumber - 1) * pageSize);
            try (ResultSet result = statement.executeQuery()) {
                orders = new ArrayList<>();
                while(result.next()) {
                    PendingOrderModel order = new PendingOrderModel();
                    order.setOrderId(result.getInt("order_m_id"));
                    order.setSupplierName(result.getString("name"));
                    order.setPrice(result.getDouble("price"));
                    Timestamp ts1 = result.getTimestamp("created_date");
                    order.setCreatedDate(new Date(ts1.getTime()));
                    orders.add(order);
                }
            }
        }
        
        return paginatedQueryEnd(con, pageNumber, pageSize, orders);
    }

    @Override
    public ViewOrderModel findById(int id, int userId, boolean isRetailer) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        
        PreparedStatement statement = null;
        
        if(isRetailer)
            statement = findByIdRetailer(con, id, userId);
        else
            statement = findByIdSupplier(con, id, userId);
        
        ResultSet result = statement.executeQuery();
        
        if(result.next()) {
            ViewOrderModel model = new ViewOrderModel();
            
            Timestamp ts1 = result.getTimestamp("created_date");
            model.setCreatedDate(new Date(ts1.getTime()));
            
            model.addProduct(getViewOrderProduct(result));
            
            while(result.next()) {
                model.addProduct(getViewOrderProduct(result));
            }
            
            return model;
        }
        
        return null;
    }
    
    private ViewOrderListModel getViewOrderProduct(ResultSet result) throws SQLException {
        ViewOrderListModel product = new ViewOrderListModel();
        
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
                                                            "JOIN supplier AS s ON s.supplier_id = om.supplier_id " +
                                                            "WHERE om.order_m_id = ? AND od.retailer_id = ?");
        
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
                                                            "JOIN retailer AS r ON r.retailer_id = od.retailer_id " +
                                                            "WHERE om.order_m_id = ? AND om.supplier_id = ?");
        
        statement.setInt(1, id);
        statement.setInt(2, supplierId);
        
        return statement;
    }

    @Override
    public PaginatedListModel<OrderHistoryModel> getOrderHistory(int supplierId, int pageNumber, int pageSize) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        
        PreparedStatement statement = con.prepareStatement("SELECT SQL_CALC_FOUND_ROWS od.order_m_id, SUM(p.price) AS price, om.created_date " +
                                                            "FROM order_m_d AS od " +
                                                            "JOIN order_m AS om ON om.order_m_id = od.order_m_id " +
                                                            "JOIN supplier AS s ON s.supplier_id = om.supplier_id " +
                                                            "JOIN order_m_d_d AS odd ON od.order_m_d_id = odd.order_m_d_id " +
                                                            "JOIN product AS p ON odd.product_id = p.product_id " +
                                                            "WHERE om.supplier_id = ? " +
                                                            "GROUP BY odd.order_m_d_id " +
                                                            "LIMIT ? OFFSET ?");
        
        statement.setInt(1, supplierId);
        statement.setInt(2, pageSize);
        statement.setInt(3, (pageNumber - 1) * pageSize);
        
        ResultSet result = statement.executeQuery();
        
        ArrayList<OrderHistoryModel> orders = new ArrayList<>();
        
        while(result.next()) {
            OrderHistoryModel order = new OrderHistoryModel();
            order.setId(result.getInt("order_m_id"));
            order.setTotal(result.getDouble("price"));
            Timestamp ts1 = result.getTimestamp("created_date");
            order.setCreatedDate(new Date(ts1.getTime()));
            orders.add(order);
        }
        
        result.close();

        return paginatedQueryEnd(con, pageNumber, pageSize, orders);
    }

    @Override
    public String confirmOrder(int orderId, int retailerId) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        
        CallableStatement statement = con.prepareCall("{CALL sp_create_invoice(?, ?, ?, ?)}");
        
        statement.setInt(1, retailerId);
        statement.setInt(2, orderId);
        statement.registerOutParameter(3, Types.BOOLEAN);
        statement.registerOutParameter(4, Types.NVARCHAR);
        
        statement.execute();
        
        boolean isSuccess = (boolean)statement.getObject(3);
        
        if(!isSuccess) {
            String errorMessage = statement.getString(4);
            return errorMessage;
        }
        
        return null;
    }
}
