/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapi.persistence.concretes;

import com.webapi.domain.entities.Product;
import com.webapi.persistence.DatabaseConnection;
import com.webapi.persistence.abstractions.IProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductRepository implements IProductRepository {

    @Override
    public boolean add(Product entity) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        int rowAffected = 0;

        con.setAutoCommit(false);
        PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCT(name, description, price, stock) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        statement.setDouble(3, entity.getPrice());
        statement.setInt(4, entity.getStock());

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
    public ArrayList<Product> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

}
