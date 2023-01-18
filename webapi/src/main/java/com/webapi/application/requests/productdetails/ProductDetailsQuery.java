package com.webapi.application.requests.productdetails;

import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.exceptions.NotFoundException;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDetailsQuery implements ISQLOperation<ProductDetailsRequest, ProductDetailsModel> {

    private static final String QUERY = "SELECT * FROM product AS p "
                                        + "LEFT JOIN productimage AS pi ON pi.product_id = p.product_id "
                                        + "LEFT JOIN retailer as r ON r.retailer_id = p.retailer_id "
                                        + "WHERE p.product_id = ?";
    private static final String NOT_FOUND_ERROR_KEY = "product";

    @Override
    public ProductDetailsModel execute(ProductDetailsRequest params) throws SQLException {
        ProductDetailsModel product;
        try (Connection con = DatabaseConnection.getConntection()) {
            PreparedStatement statement = con.prepareStatement(QUERY);
            statement.setInt(1, params.getId());
            try (ResultSet result = statement.executeQuery()) {
                product = null;
                if (result.next()) {
                    product = new ProductDetailsModel();
                    
                    product.setId(params.getId());
                    product.setName(result.getString("name"));
                    product.setDescription(result.getString("description"));
                    product.setStock(result.getInt("stock"));
                    product.setPrice(result.getDouble("price"));
                    product.setDiscount(result.getDouble("discount"));
                    product.addImage(result.getString("path"));
                    
                    while (result.next()) {
                        product.addImage(result.getString("path"));
                    }
                } else {
                    con.close();
                    throw new NotFoundException(NOT_FOUND_ERROR_KEY, String.valueOf(params.getId()));
                }
            }
        }

        return product;
    }

}
