package com.webapi.application.requests.createproduct;

import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import com.webapi.application.abstractions.ISQLOperation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateProductCommand implements ISQLOperation<CreateProductRequest, Boolean> {

    private static final String PRODUCT_QUERY = "INSERT INTO PRODUCT(name, description, price, stock, supplier_id) VALUES(?,?,?,?,?)";
    private static final String PRODUCT_IMAGE_QUERY = "INSERT INTO PRODUCTIMAGE(product_id, path) VALUES(?,?)";
    private static final int PRODUCT_NAME_INDEX = 1;
    private static final int PRODUCT_DESCRIPTION_INDEX = 2;
    private static final int PRODUCT_PRICE_INDEX = 3;
    private static final int PRODUCT_STOCK_INDEX = 4;
    private static final int PRODUCT_SUPPLIER_INDEX = 5;
    private static final int PRODUCT_IMAGE_ID_INDEX = 1;
    private static final int PRODUCT_IMAGE_PATH_INDEX = 2;

    @Override
    public Boolean execute(CreateProductRequest params) throws SQLException {
        try (Connection con = DatabaseConnection.getConntection()) {
            int rowAffected;

            con.setAutoCommit(false);
            try (PreparedStatement productStatement = con.prepareStatement(PRODUCT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                productStatement.setString(PRODUCT_NAME_INDEX, params.getName());
                productStatement.setString(PRODUCT_DESCRIPTION_INDEX, params.getDescription());
                productStatement.setDouble(PRODUCT_PRICE_INDEX, params.getPrice());
                productStatement.setInt(PRODUCT_STOCK_INDEX, params.getStock());
                productStatement.setInt(PRODUCT_SUPPLIER_INDEX, params.getSupplierId());

                rowAffected = productStatement.executeUpdate();
                if (rowAffected == 1) {
                    ResultSet keys = productStatement.getGeneratedKeys();

                    if (keys.next()) {
                        int productId = keys.getInt(1);

                        try (PreparedStatement productImageStatement = con.prepareStatement(PRODUCT_IMAGE_QUERY)) {
                            for (String path : params.getProductImages()) {
                                productImageStatement.setInt(PRODUCT_IMAGE_ID_INDEX, productId);
                                productImageStatement.setString(PRODUCT_IMAGE_PATH_INDEX, path);
                                productImageStatement.addBatch();
                            }
                            productImageStatement.executeBatch();
                        }
                    }
                }
            }
            con.commit();
        }
        return true;
    }
}
