
package com.webapi.application.requests.outofstockproducts;

import com.webapi.application.concretes.PaginatedSQLQuery;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.requests.retailerproducts.RetailerProductsListModel;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OutOfStockProductsQuery extends PaginatedSQLQuery<OutOfProductsRequest, RetailerProductsListModel> {

    private static final String QUERY = "SELECT SQL_CALC_FOUND_ROWS p.product_id, p.name, p.price, p.stock, p.discount, MIN(pi.path) AS path " + 
                                        "FROM product AS p " +
                                        "LEFT JOIN productimage AS pi ON pi.product_id = p.product_id " +
                                        "WHERE p.retailer_id = ? AND p.stock = 0 " +
                                        "GROUP BY pi.product_id, p.product_id " +
                                        "LIMIT ? OFFSET ?";
    
    @Override
    public PaginatedListModel<RetailerProductsListModel> execute(OutOfProductsRequest params) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        
        ArrayList<RetailerProductsListModel> products;
        try (PreparedStatement statement = con.prepareStatement(QUERY)) {
            statement.setInt(1, params.getRetailerId());
            statement.setInt(2, params.getPageSize());
            statement.setInt(3, (params.getPageNumber() - 1) * params.getPageSize());
            try (ResultSet result = statement.executeQuery()) {
                products = new ArrayList<>();
                while (result.next()) {
                    
                    RetailerProductsListModel product = new RetailerProductsListModel();
                    
                    product.setId(result.getInt("product_id"));
                    product.setName(result.getString("name"));
                    product.setStock(result.getInt("stock"));
                    product.setPrice(result.getDouble("price"));
                    product.setDiscount(result.getDouble("discount"));
                    product.setImagePath(result.getString("path"));
                    
                    products.add(product);
                }
            }
        }

        return paginatedQueryEnd(con, params.getPageNumber(), params.getPageSize(), products);
    }


}
