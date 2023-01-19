
package com.webapi.application.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ModelValidationExceptionHandler implements ExceptionMapper<ModelValidationException> {

    @Override
    public Response toResponse(ModelValidationException exception) {
        return Response.status(400).entity(exception.getErrors()).build();
    }

}
