package com.webapi.application.requests.supplierlogin;

import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.models.AccessTokenFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SupplierLoginCommand implements ISQLOperation<SupplierLoginRequest, AccessTokenFactory> {
    @Override
    public AccessTokenFactory execute(SupplierLoginRequest params) throws SQLException {
        try (Connection con = DatabaseConnection.getConntection()) {
            PreparedStatement statement = con.prepareStatement("SELECT supplier_id, password_hash FROM SUPPLIER WHERE email = ?");
            statement.setString(1, params.getEmail());
            if (statement.execute()) {
                ResultSet result = statement.getResultSet();
                if (result.next()) {
                    AccessTokenFactory accessTokenFactory = new AccessTokenFactory();
                    accessTokenFactory.setIsRetailer(false);
                    accessTokenFactory.setUserId(result.getInt("supplier_id"));
                    accessTokenFactory.setPasswordHash(result.getString("password_hash"));
                    accessTokenFactory.setPassword(params.getPassword());
                    return accessTokenFactory;
                }
            }
        }
        return null;
    }
}