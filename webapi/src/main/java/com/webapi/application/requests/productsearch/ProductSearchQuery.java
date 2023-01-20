
package com.webapi.application.requests.productsearch;

import com.webapi.application.concretes.PaginatedSQLQuery;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductSearchQuery extends PaginatedSQLQuery<ProductSearchRequest, ProductSearchModel> {
    private static final String QUERY = "SELECT SQL_CALC_FOUND_ROWS p.product_id, p.name, p.price, p.stock, LEFT(p.description, 300) AS description, p.supplier_id, MIN(pi.path) AS path, p.discount, r.name AS supplier_name FROM product AS p " +
                                            "LEFT JOIN productimage AS pi ON pi.product_id = p.product_id " +
                                            "LEFT JOIN supplier as r ON r.supplier_id = p.supplier_id " +
                                            "WHERE MATCH(p.name) AGAINST(?) " +
                                            "GROUP BY pi.product_id, p.product_id " +
                                            "LIMIT ? OFFSET ?";
    @Override
    public PaginatedListModel<ProductSearchModel> execute(ProductSearchRequest params) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        ArrayList<ProductSearchModel> products;
        try (PreparedStatement statement = con.prepareStatement(QUERY)) {
            
            statement.setString(1, params.getQuery());
            statement.setInt(2, params.getPageSize());
            statement.setInt(3, (params.getPageNumber() - 1) * params.getPageSize());
            
            try (ResultSet result = statement.executeQuery()) {
                products = new ArrayList<>();
                
                while (result.next()) {
                    
                    ProductSearchModel product = new ProductSearchModel();
                    
                    product.setId(result.getInt("product_id"));
                    product.setName(result.getString("name"));
                    product.setDescription(result.getString("description"));
                    product.setStock(result.getInt("stock"));
                    product.setPrice(result.getDouble("price"));
                    product.setDiscount(result.getDouble("discount"));
                    product.setSupplierId(result.getInt("supplier_id"));
                    product.setSupplierName(result.getString("supplier_name"));
                    product.setImagePath(result.getString("path"));
                    
                    products.add(product);
                }
            }
        }
        return paginatedQueryEnd(con, params.getPageNumber(), params.getPageSize(), products);
    }

}
