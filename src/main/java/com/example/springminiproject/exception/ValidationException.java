package com.example.springminiproject.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@NoArgsConstructor
public class ValidationException extends RuntimeException{
    private HttpStatus status;
    private String detail;
    private Map<String, String> errors;

    public ValidationException(HttpStatus status, String message, Map<String, String> errors) {
        super(message);
        this.status = status;
        this.detail = message;
        this.errors = errors;
    }

}
