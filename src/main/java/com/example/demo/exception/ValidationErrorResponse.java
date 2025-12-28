package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public static class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> fieldErrors;
    
    public ValidationErrorResponse(LocalDateTime timestamp, int status, String error, 
                                 String message, String path, Map<String, String> fieldErrors) {
        super(timestamp, status, error, message, path);
        this.fieldErrors = fieldErrors;
    }
    
    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
    
    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}