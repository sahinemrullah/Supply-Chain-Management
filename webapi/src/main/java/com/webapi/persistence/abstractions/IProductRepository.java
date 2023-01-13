package com.webapi.persistence.abstractions;

import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.models.product.ProductSearchModel;
import com.webapi.application.models.retailer.ProductListModel;
import com.webapi.domain.entities.Product;
import java.sql.SQLException;

public interface IProductRepository extends IRepository<Product> {

    public Product findById(int id) throws SQLException ;

    public PaginatedListModel<ProductSearchModel> paginatedSearch(String query, int pageNumber, int pageSize) throws SQLException;

    public boolean editStock(int id, int stock) throws SQLException ;

    public PaginatedListModel<ProductListModel> getProductsInStockFor(int retailerId, int pageNumber, int pageSize) throws SQLException;
    
    public PaginatedListModel<ProductListModel> getProductsOutOfStockFor(int retailerId, int pageNumber, int pageSize) throws SQLException;

    public boolean editDiscount(int id, double discount) throws SQLException;
    
}
