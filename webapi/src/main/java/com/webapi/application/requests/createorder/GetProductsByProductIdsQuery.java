
package com.webapi.application.requests.createorder;

import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GetProductsByProductIdsQuery implements ISQLOperation<Set<Integer>, List<CreateOrderProductModel>> {

    @Override
    public List<CreateOrderProductModel> execute(Set<Integer> ids) throws SQLException {
        List<CreateOrderProductModel> products;
        
        try (Connection con = DatabaseConnection.getConntection()) {
            
            String sql = "SELECT product_id, retailer_id, discount FROM product";
            
            if(ids != null && !ids.isEmpty()) {
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
            
            try (ResultSet result = statement.executeQuery()) {
                
                products = new ArrayList<>();
                
                while(result.next()) {
                    CreateOrderProductModel product = new CreateOrderProductModel();
                    
                    product.setId(result.getInt("product_id"));
                    product.setRetailerId(result.getInt("retailer_id"));
                    product.setDiscount(result.getDouble("discount"));
                    products.add(product);
                }
            }
        }
        
        return products;
    }

}
