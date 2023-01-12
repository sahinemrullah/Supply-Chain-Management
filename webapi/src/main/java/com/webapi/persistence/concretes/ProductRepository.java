/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapi.persistence.concretes;

import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.models.ProductSearchModel;
import com.webapi.domain.entities.Product;
import com.webapi.persistence.DatabaseConnection;
import com.webapi.persistence.abstractions.IProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {

    @Override
    public boolean add(Product entity) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        int rowAffected = 0;

        con.setAutoCommit(false);
        PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCT(name, description, price, stock, retailer_id) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        statement.setDouble(3, entity.getPrice());
        statement.setInt(4, entity.getStock());
        statement.setInt(5, entity.getRetailerId());

        rowAffected = statement.executeUpdate();
        if (rowAffected == 1) {
            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()) {
                int productId = keys.getInt(1);

                PreparedStatement productImageStatement = con.prepareStatement("INSERT INTO PRODUCTIMAGE(product_id, path) VALUES(?,?)");

                for (String path : entity.getProductImages()) {
                    productImageStatement.setInt(1, productId);
                    productImageStatement.setString(2, path);
                    productImageStatement.addBatch();
                }
                productImageStatement.executeBatch();
            }
        }
        con.commit();
        return true;

    }

    @Override
    public void delete(Product entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Product entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Product> getAll(Integer[] ids) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        
        String sql = "SELECT product_id, retailer_id FROM product";
        
        if(ids != null && ids.length > 0) {
            StringBuilder builder = new StringBuilder();
            for(int id : ids) {
                builder.append(id);
                builder.append(",");
            }

            String result = builder.toString();
            result = result.substring(0, result.length() - 1 );

            sql = String.format("%s WHERE product_id IN(%s)", sql, result);
        }
        
        PreparedStatement statement = con.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        
        ArrayList<Product> products = new ArrayList<>();
        
        while(result.next()) {
            Product product = new Product();
            product.setId(result.getInt("product_id"));
            product.setRetailerId(result.getInt("retailer_id"));
            products.add(product);
        }
        
        return products;
    }

    @Override
    public Product findById(int id) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM product AS p " +
                                                            "JOIN productimage AS pi ON pi.product_id = p.product_id " +
                                                            "LEFT JOIN retailer as r ON r.retailer_id = p.retailer_id " +
                                                            "WHERE p.product_id = ?");
        
        statement.setInt(1, id);
        
        ResultSet result = statement.executeQuery();
        
        if(result.next()) {
            Product product = new Product();
            product.setId(id);
            product.setName(result.getString("name"));
            product.setDescription(result.getString("description"));
            product.setStock(result.getInt("stock"));
            product.setPrice(result.getDouble("price"));
            product.setDiscount(0);
            product.addImage(result.getString("path"));
            while(result.next()) {
                product.addImage(result.getString("path"));
            }
            return product;
        } else {
            return null;
        }
    }

    @Override
    public PaginatedListModel<ProductSearchModel> paginatedSearch(String query, int pageNumber, int pageSize) throws SQLException {
        Connection con = DatabaseConnection.getConntection();

        PreparedStatement statement = con.prepareStatement("SELECT SQL_CALC_FOUND_ROWS p.product_id, p.name, p.price, p.stock, LEFT(p.description, 300) AS description, p.retailer_id, MIN(pi.path) AS path, r.name AS retailer_name FROM product AS p " +
                                                            "JOIN productimage AS pi ON pi.product_id = p.product_id " +
                                                            "LEFT JOIN retailer as r ON r.retailer_id = p.retailer_id " +
                                                            "WHERE MATCH(p.name) AGAINST(?) " +
                                                            "GROUP BY pi.product_id " +
                                                            "LIMIT ? OFFSET ?");
        PreparedStatement numberOfRecordsStatement = con.prepareStatement("SELECT FOUND_ROWS()");

        statement.setString(1, query);
        statement.setInt(2, pageSize);
        statement.setInt(3, (pageNumber - 1) * pageSize);

        ResultSet result = statement.executeQuery();

        ArrayList<ProductSearchModel> products = new ArrayList<>();

        while (result.next()) {
            ProductSearchModel product = new ProductSearchModel();
            product.setId(result.getInt("product_id"));
            product.setName(result.getString("name"));
            product.setDescription(result.getString("description"));
            product.setStock(result.getInt("stock"));
            product.setPrice(result.getDouble("price"));
            product.setDiscount(0);
            product.setRetailerId(result.getInt("retailer_id"));
            product.setRetailerName(result.getString("retailer_name"));
            product.setImagePath(result.getString("path"));

            products.add(product);
        }

        result.close();

        result = numberOfRecordsStatement.executeQuery();

        int numberOfRecords = 0;

        if (result.next()) {
            numberOfRecords = result.getInt(1);
        }

        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / pageSize);
        
        PaginatedListModel<ProductSearchModel> model = new PaginatedListModel<>();
        
        model.setItems(products);
        model.setPageNumber(pageNumber);
        model.setPageSize(pageSize);
        model.setNumberOfRecords(numberOfRecords);
        model.setNumberOfPages(numberOfPages);

        return model;
    }

}
