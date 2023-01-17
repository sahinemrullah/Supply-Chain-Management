package com.webapi.application.abstractions;

public interface IRequestHandler<T extends IRequest, U extends Object> {
    public IResult<U> handle(T request);
}
