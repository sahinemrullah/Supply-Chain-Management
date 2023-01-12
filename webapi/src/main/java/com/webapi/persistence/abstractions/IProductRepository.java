/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapi.persistence.abstractions;

import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.models.ProductSearchModel;
import com.webapi.domain.entities.Product;
import java.sql.SQLException;

public interface IProductRepository extends IRepository<Product> {

    public Product findById(int id) throws SQLException ;

    public PaginatedListModel<ProductSearchModel> paginatedSearch(String query, int pageNumber, int pageSize) throws SQLException;
    
}
