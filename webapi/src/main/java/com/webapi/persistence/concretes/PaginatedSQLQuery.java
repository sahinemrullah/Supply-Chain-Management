
package com.webapi.persistence.concretes;

import com.webapi.persistence.abstractions.ISQLOperation;
import com.webapi.application.models.PaginatedListModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class PaginatedSQLQuery<T extends Object, U extends Object> implements ISQLOperation<T, PaginatedListModel<U>> {
    protected <U> PaginatedListModel<U> paginatedQueryEnd(Connection con, int pageNumber, int pageSize, List<U> items) throws SQLException {
        PaginatedListModel<U> model;
        try (PreparedStatement statement = con.prepareStatement("SELECT FOUND_ROWS()");
                ResultSet result = statement.executeQuery()) {
            
            int numberOfRecords = 0;
            
            if (result.next()) {
                numberOfRecords = result.getInt(1);
            }   
            
            int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / pageSize);
            
            model = new PaginatedListModel<>();
            model.setItems(items);
            model.setPageNumber(pageNumber);
            model.setPageSize(pageSize);
            model.setNumberOfRecords(numberOfRecords);
            model.setNumberOfPages(numberOfPages);
        }
        
        con.close();
        
        return model;
    }
}
