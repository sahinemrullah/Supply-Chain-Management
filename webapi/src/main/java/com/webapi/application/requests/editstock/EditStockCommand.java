package com.webapi.application.requests.editstock;

import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditStockCommand implements ISQLOperation<EditStockRequest, Boolean> {

    private static final String QUERY = "UPDATE product SET stock = ? WHERE product_id = ?";
    private static final int STOCK_INDEX = 1;
    private static final int PRODUCT_ID_INDEX = 2;
    private static final int EXPECTED_ROW_UPDATE_COUNT = 1;

    @Override
    public Boolean execute(EditStockRequest params) throws SQLException {
        int rowAffected;
        try (Connection con = DatabaseConnection.getConntection(); PreparedStatement statement = con.prepareStatement(QUERY)) {

            statement.setDouble(STOCK_INDEX, params.getStock() / 100.d);
            statement.setInt(PRODUCT_ID_INDEX, params.getId());

            rowAffected = statement.executeUpdate();
        }
        return rowAffected == EXPECTED_ROW_UPDATE_COUNT;
    }

}
