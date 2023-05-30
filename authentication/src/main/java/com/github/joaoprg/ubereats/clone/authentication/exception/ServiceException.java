package com.github.joaoprg.ubereats.clone.authentication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ServiceException extends RuntimeException {

    private final ServiceErrorCode errorCode;

    public ServiceException(
            final Throwable cause,
            final ServiceErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
