package com.webapi.application.requests.retailerlogin;

import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.models.AccessTokenFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RetailerLoginCommand implements ISQLOperation<RetailerLoginRequest, AccessTokenFactory> {
    @Override
    public AccessTokenFactory execute(RetailerLoginRequest params) throws SQLException {
        try (Connection con = DatabaseConnection.getConntection()) {
            PreparedStatement statement = con.prepareStatement("SELECT retailer_id, password_hash FROM RETAILER WHERE email = ?");
            statement.setString(1, params.getEmail());
            if (statement.execute()) {
                ResultSet result = statement.getResultSet();
                if (result.next()) {
                    AccessTokenFactory accessTokenFactory = new AccessTokenFactory();
                    accessTokenFactory.setIsRetailer(true);
                    accessTokenFactory.setUserId(result.getInt("retailer_id"));
                    accessTokenFactory.setPasswordHash(result.getString("password_hash"));
                    accessTokenFactory.setPassword(params.getPassword());
                    return accessTokenFactory;
                }
            }
        }
        return null;
    }
}
