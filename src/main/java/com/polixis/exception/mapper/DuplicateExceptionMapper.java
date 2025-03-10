package com.polixis.exception.mapper;

import com.polixis.exception.DuplicateException;
import com.polixis.exception.ErrorResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DuplicateExceptionMapper implements ExceptionMapper<DuplicateException> {

    @Override
    public Response toResponse(DuplicateException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                Response.Status.BAD_REQUEST.getStatusCode(),
                exception.getMessage()
        );
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
