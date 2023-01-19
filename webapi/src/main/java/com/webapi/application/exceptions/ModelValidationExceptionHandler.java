
package com.webapi.application.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ModelValidationExceptionHandler implements ExceptionMapper<ModelValidationException> {

    @Override
    public Response toResponse(ModelValidationException exception) {
        return Response.status(Status.BAD_REQUEST).entity(exception.getErrors()).build();
    }

}
