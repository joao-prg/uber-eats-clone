package com.github.joaoprg.ubereats.clone.authentication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ServiceException extends RuntimeException {

    private final String message;
    private final int httpStatusCode;
    private final String description;

    public ServiceException(final String message, final int httpStatusCode) {
        this(message, httpStatusCode, null);
    }

    public ServiceException(
            final int httpStatusCode,
            final Throwable cause) {

        super(cause);
        this.message = null;
        this.description = null;
        this.httpStatusCode = httpStatusCode;
    }


    public ServiceException(
            final String message,
            final int httpStatusCode,
            final String description,
            final Throwable cause) {

        super(cause);
        this.message = message;
        this.description = description;
        this.httpStatusCode = httpStatusCode;
    }

}
