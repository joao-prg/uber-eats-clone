package com.github.joaoprg.ubereats.clone.register.exception.mapper;

import com.github.joaoprg.ubereats.clone.register.exception.ExceptionPayload;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Slf4j
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<ExceptionPayload.FieldException> fieldExceptions = e.getConstraintViolations()
                .stream()
                .map(constraintViolation -> new ExceptionPayload.FieldException(
                        constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage())).toList();
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ExceptionPayload.builder().fieldExceptions(fieldExceptions).build())
                .build();
    }
}
