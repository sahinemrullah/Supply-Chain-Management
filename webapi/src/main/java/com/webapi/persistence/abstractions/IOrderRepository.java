package com.webapi.persistence.abstractions;

import com.webapi.domain.entities.Order;
import com.webapi.domain.entities.Product;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IOrderRepository extends IRepository<Order> {

    int createOrder(int supplierId, List<Product> products, Map<Integer, Integer> quantities) throws SQLException ;
    
}
