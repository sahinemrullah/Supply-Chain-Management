package com.webapi.application.requests.editdiscount;

import com.webapi.persistence.abstractions.ISQLOperation;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditDiscountCommand implements ISQLOperation<EditDiscountRequest, Boolean> {

    private static final String QUERY = "UPDATE product SET discount = ? WHERE product_id = ?";
    private static final int DISCOUNT_INDEX = 1;
    private static final int PRODUCT_ID_INDEX = 2;
    private static final int EXPECTED_ROW_UPDATE_COUNT = 1;

    @Override
    public Boolean execute(EditDiscountRequest params) throws SQLException {
        int rowAffected;
        try (Connection con = DatabaseConnection.getConntection(); PreparedStatement statement = con.prepareStatement(QUERY)) {

            statement.setDouble(DISCOUNT_INDEX, params.getDiscount() / 100.d);
            statement.setInt(PRODUCT_ID_INDEX, params.getId());

            rowAffected = statement.executeUpdate();
        }
        return rowAffected == EXPECTED_ROW_UPDATE_COUNT;
    }

}
