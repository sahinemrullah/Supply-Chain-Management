
package com.webapi.application.requests.retailerregister;

import com.webapi.persistence.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.utils.EncryptionUtils;
import java.sql.PreparedStatement;

public class RetailerRegisterCommand implements ISQLOperation<RetailerRegisterRequest, Boolean> {
    private final String query = "INSERT INTO RETAILER(name, email, phone_number, password_hash) VALUES(?,?,?,?)";
    @Override
    public Boolean execute(RetailerRegisterRequest params) throws SQLException {
        Connection con = DatabaseConnection.getConntection();

        PreparedStatement statement = con.prepareStatement(query);

        statement.setString(1, params.getName());
        statement.setString(2, params.getEmail());
        statement.setString(3, params.getPhoneNumber());
        statement.setString(4, EncryptionUtils.hashString(params.getPassword()));

        int rowAffected = statement.executeUpdate();
        return rowAffected == 1;
    }
}
