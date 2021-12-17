package com.dangnghia.PPMFullStack.exceptions;

import org.springframework.http.HttpStatus;

public class ProjectNameException extends AbstractBusinessException{
    public ProjectNameException(String message) {
        super(new ProjectNameExceptionResponse(message),HttpStatus.BAD_REQUEST);
    }
}
