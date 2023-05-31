package com.github.joaoprg.ubereats.clone.register.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.JDBCConnectionException;

import javax.json.bind.JsonbException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class GenericExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    @Override
    public Response toResponse(final Exception exception) {
        final ExceptionPayload exceptionPayload;
        final ServiceErrorCode serviceErrorCode;

        if ((exception.getCause() instanceof JsonParseException) || (exception.getCause() instanceof JsonbException)
                || (exception instanceof IllegalArgumentException) || (exception instanceof InvalidFormatException)) {
            serviceErrorCode = ServiceErrorCode.INVALID_INPUT;
        } else if (exception.getCause() instanceof JDBCConnectionException) {
            serviceErrorCode = ServiceErrorCode.DATABASE_CONNECTION_ERROR;
        } else {
            serviceErrorCode = ServiceErrorCode.INTERNAL_SERVER_ERROR;
        }

        exceptionPayload = ExceptionPayload.builder()
                .code(serviceErrorCode.getCode())
                .message(serviceErrorCode.getMessage())
                .httpStatusCode(serviceErrorCode.getHttpStatusCode())
                .build();

        log.error("{} exception caught: {}", exception.getClass().getName(), exceptionPayload, exception);

        return Response
                .status(exceptionPayload.getHttpStatusCode())
                .entity(exceptionPayload)
                .build();
    }
}
