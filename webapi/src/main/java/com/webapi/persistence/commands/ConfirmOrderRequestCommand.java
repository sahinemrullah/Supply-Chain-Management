
package com.webapi.persistence.commands;

import com.webapi.application.requests.confirmorder.ConfirmOrderRequest;
import com.webapi.persistence.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import com.webapi.persistence.abstractions.ISQLOperation;

public class ConfirmOrderRequestCommand implements ISQLOperation<ConfirmOrderRequest, String> {
    private final String query = "{CALL sp_create_invoice(?, ?, ?, ?)}";
    private final int supplierIdIndex = 1;
    private final int orderIdIndex = 2;
    private final int isSuccessIndex = 3;
    private final int errorMessageIndex = 4;
    @Override
    public String execute(ConfirmOrderRequest params) throws SQLException {
        Connection con = DatabaseConnection.getConntection();
        
        CallableStatement statement = con.prepareCall(query);
        
        statement.setInt(supplierIdIndex, params.getUserId());
        statement.setInt(orderIdIndex, params.getId());
        statement.registerOutParameter(isSuccessIndex, Types.BOOLEAN);
        statement.registerOutParameter(errorMessageIndex, Types.NVARCHAR);
        
        statement.execute();
        
        boolean isSuccess = (boolean)statement.getObject(isSuccessIndex);
        
        if(!isSuccess) {
            return statement.getString(errorMessageIndex);
        }
        
        return null;
    }
}
