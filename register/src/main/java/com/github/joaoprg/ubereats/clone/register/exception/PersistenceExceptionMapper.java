package com.github.joaoprg.ubereats.clone.register.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.JDBCConnectionException;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {
    @Override
    public Response toResponse(final PersistenceException exception) {
        final ExceptionPayload exceptionPayload;

        if (exception.getCause() instanceof JDBCConnectionException) {
            exceptionPayload = ExceptionPayload.builder()
                    .code(ServiceErrorCode.DATABASE_CONNECTION_ERROR.getCode())
                    .message(ServiceErrorCode.DATABASE_CONNECTION_ERROR.getMessage())
                    .httpStatusCode(ServiceErrorCode.DATABASE_CONNECTION_ERROR.getHttpStatusCode())
                    .build();
        }
        else {
            exceptionPayload = ExceptionPayload.builder()
                    .code(ServiceErrorCode.INTERNAL_SERVER_ERROR.getCode())
                    .message(ServiceErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                    .httpStatusCode(ServiceErrorCode.INTERNAL_SERVER_ERROR.getHttpStatusCode())
                    .build();
        }

        log.error("{} exception caught: {}", PersistenceException.class.getName(), exceptionPayload, exception);

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .entity(exceptionPayload)
                .build();
    }
}
