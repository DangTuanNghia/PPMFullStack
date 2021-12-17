package com.dangnghia.PPMFullStack.exceptions;

import org.springframework.http.HttpStatus;

public abstract class  AbstractBusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final Object error;
    private final HttpStatus errorStatus;

    public AbstractBusinessException(Object error, HttpStatus errorStatus) {
        this.error = error;
        this.errorStatus = errorStatus;
    }

    public Object getError() {
        return error;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }
}
