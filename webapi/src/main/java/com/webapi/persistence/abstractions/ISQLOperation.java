package com.webapi.persistence.abstractions;

import java.sql.SQLException;

public interface ISQLOperation<T extends Object, U extends Object> {
    public U execute(T params) throws SQLException ;
}
