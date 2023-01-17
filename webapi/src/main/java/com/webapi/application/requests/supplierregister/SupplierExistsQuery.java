package com.webapi.application.requests.supplierregister;

import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.utils.StringUtils;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierExistsQuery implements ISQLOperation<String, Boolean> {

    private final String query = "SELECT email FROM supplier WHERE email = ?";
    private final int emailIndex = 1;

    @Override
    public Boolean execute(String email) throws SQLException {
        Connection con = DatabaseConnection.getConntection();

        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(emailIndex, email);
        if (statement.execute()) {
            ResultSet result = statement.getResultSet();
            if (result.next()) {
                return StringUtils.isEqual(email, result.getString(emailIndex));
            }
        }
        return false;
    }

}
