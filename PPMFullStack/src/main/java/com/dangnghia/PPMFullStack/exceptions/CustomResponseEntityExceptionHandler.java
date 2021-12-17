package com.dangnghia.PPMFullStack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler {
//    @ExceptionHandler(value = ProjectIdException.class)
//    public final ResponseEntity<ProjectIdExceptionResponse> handleProjectIdException(ProjectIdException ex, WebRequest request){
//        ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public final ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    errorMap.put(((FieldError)error).getField(), error.getDefaultMessage());
                });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AbstractBusinessException.class)
    public final ResponseEntity<Object> handleMethodArgumentNotValidException(
            AbstractBusinessException ex){
        return new ResponseEntity<>(ex.getError(), ex.getErrorStatus());
    }
}
