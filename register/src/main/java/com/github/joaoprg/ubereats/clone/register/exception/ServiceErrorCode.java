package com.github.joaoprg.ubereats.clone.register.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ws.rs.core.Response;

@Getter
@AllArgsConstructor
public enum ServiceErrorCode {

    RESTAURANT_NOT_FOUND("1040400", "Restaurant not found.", Response.Status.NOT_FOUND.getStatusCode()),
    DISH_NOT_FOUND("1040401", "Dish not found.", Response.Status.NOT_FOUND.getStatusCode());

    private final String code;

    private final String message;

    private final int httpStatusCode;
}
