package com.github.joaoprg.ubereats.clone.register.exception.mapper;

import com.github.joaoprg.ubereats.clone.register.exception.ExceptionPayload;
import com.github.joaoprg.ubereats.clone.register.exception.ServiceErrorCode;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException> {

    @Override
    public Response toResponse(final NotAuthorizedException exception) {
        final ServiceErrorCode errorCode = ServiceErrorCode.UNAUTHORIZED_ACCESS;

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