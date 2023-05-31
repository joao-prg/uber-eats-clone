package com.github.joaoprg.ubereats.clone.register.exception;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(final ServiceException exception) {
        final ExceptionPayload exceptionPayload = ExceptionPayload
                .builder()
                .code(exception.getErrorCode().getCode())
                .message(exception.getErrorCode().getMessage())
                .httpStatusCode(exception.getErrorCode().getHttpStatusCode())
                .build();
        log.error("{} exception caught: {}", ServiceException.class.getName(), exceptionPayload, exception);

        return Response.status(exception.getErrorCode().getHttpStatusCode())
                .entity(exceptionPayload)
                .build();
    }
}