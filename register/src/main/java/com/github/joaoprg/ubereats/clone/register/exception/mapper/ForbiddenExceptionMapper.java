package com.github.joaoprg.ubereats.clone.register.exception.mapper;

import com.github.joaoprg.ubereats.clone.register.exception.ExceptionPayload;
import com.github.joaoprg.ubereats.clone.register.exception.ServiceErrorCode;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {
    @Override
    public Response toResponse(final ForbiddenException exception) {
        final ServiceErrorCode errorCode = ServiceErrorCode.FORBIDDEN_ACCESS;

        final ExceptionPayload errorPayload = ExceptionPayload
                .builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return Response
                .status(errorCode.getHttpStatusCode())
                .entity(errorPayload)
                .build();
    }
}
