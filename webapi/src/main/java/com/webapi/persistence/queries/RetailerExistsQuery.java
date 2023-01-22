package com.webapi.persistence.queries;

import com.webapi.persistence.abstractions.ISQLOperation;
import com.webapi.application.utils.StringUtils;
import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetailerExistsQuery implements ISQLOperation<String, Boolean> {

    private final String query = "SELECT email FROM RETAILER WHERE email = ?";
    private final int emailIndex = 1;

    @Override
    public Boolean execute(String email) throws SQLException {
        Connection con = DatabaseConnection.getConntection();

        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(emailIndex, email);
        if (statement.execute()) {
            ResultSet result = statement.getResultSet();
            return result.next();
        }
        return false;
    }

}
