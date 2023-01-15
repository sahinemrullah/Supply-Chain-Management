package com.webapi.persistence.abstractions;

import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.models.order.ViewOrderModel;
import com.webapi.application.models.order.PendingOrderModel;
import com.webapi.application.models.supplier.OrderHistoryModel;
import com.webapi.domain.entities.Order;
import com.webapi.domain.entities.Product;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IOrderRepository extends IRepository<Order> {

    public int createOrder(int supplierId, Map<Integer, List<Product>> productsByRetailer, Map<Integer, Integer> quantities) throws SQLException ;

    public PaginatedListModel<PendingOrderModel> getPendingOrders(int retailerId, int pageNumber, int pageSize) throws SQLException ;

    public ViewOrderModel findById(int id, int userId, boolean isRetailer) throws SQLException ;

    public PaginatedListModel<OrderHistoryModel> getOrderHistory(int supplierId, int pageNumber, int i) throws SQLException ;

    public String confirmOrder(int orderId, int retailerId) throws SQLException ;
}
