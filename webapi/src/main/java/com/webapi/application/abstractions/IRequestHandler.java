package com.webapi.application.abstractions;

import java.sql.SQLException;

public interface IRequestHandler<T extends IRequest, U extends Object> {
    public IResult<U> handle(T request) throws SQLException;
}
