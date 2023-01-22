
package com.webapi.persistence.queries;

import com.webapi.persistence.abstractions.ISQLOperation;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExistsQuery implements ISQLOperation<Integer, Boolean> {
    
    private static final String QUERY = "SELECT product_id FROM product WHERE product_id = ?";
    private static final int PRODUCT_ID_INDEX = 1;
    
    @Override
    public Boolean execute(Integer productId) throws SQLException {
        boolean productExists;
        try (Connection con = DatabaseConnection.getConntection()) {
            PreparedStatement statement = con.prepareStatement(QUERY);
            statement.setInt(PRODUCT_ID_INDEX, productId);
            ResultSet result = statement.executeQuery();
            productExists = result.next();
        }
        
        return productExists;
    }

}
