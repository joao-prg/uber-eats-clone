package com.github.joaoprg.ubereats.clone.register.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ws.rs.core.Response;

@Getter
@AllArgsConstructor
public enum ServiceErrorCode {

    // 400
    INVALID_INPUT("1040000", "Invalid input.", Response.Status.BAD_REQUEST.getStatusCode()),
    // 401
    UNAUTHORIZED_ACCESS("1040100", "Unauthorized access.", Response.Status.UNAUTHORIZED.getStatusCode()),
    FORBIDDEN_ACCESS("1040101", "Forbidden access.", Response.Status.FORBIDDEN.getStatusCode()),
    // 404
    RESTAURANT_NOT_FOUND("1040400", "Restaurant not found.", Response.Status.NOT_FOUND.getStatusCode()),
    DISH_NOT_FOUND("1040401", "Dish not found.", Response.Status.NOT_FOUND.getStatusCode()),
    // 500
    INTERNAL_SERVER_ERROR("1050000", "Internal server error.", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()),
    DATABASE_CONNECTION_ERROR("1050001", "Error connecting to the database.", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

    private final String code;

    private final String message;

    private final int httpStatusCode;
}
