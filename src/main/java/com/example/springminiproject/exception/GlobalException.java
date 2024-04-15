package com.example.springminiproject.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException notFoundException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                notFoundException.getMessage()
        );
        problemDetail.setTitle("Not Found");
        problemDetail.setProperty("time", LocalDateTime.now());
        return problemDetail;
    }
    @ExceptionHandler(PasswordException.class)
    public ProblemDetail handlePasswordException(PasswordException passwordException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                passwordException.getMessage()
        );
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("time", LocalDateTime.now());
        return problemDetail;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        for(var fieldError: ex.getBindingResult().getFieldErrors()){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("Errors:",errors);
        return  problemDetail;

    }
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException ex){
        Map<String, String> errors = new HashMap<>();
        for(var parameterErrors: ex.getAllValidationResults()){
            String parameterName =parameterErrors.getMethodParameter().getParameterName();
            for( var error: parameterErrors.getResolvableErrors()){
                errors.put(parameterName,error.getDefaultMessage());
            }
        }
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("Errors:",errors);
        return  problemDetail;
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BadRequestBodyException> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        BadRequestBodyException errorResponse = new BadRequestBodyException();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage("Bad request body");
        errorResponse.setPath(request.getRequestURI());
        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(com.example.springminiproject.exception.ValidationException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("type", "about:blank");
        response.put("title", "Bad Request");
        response.put("status", ex.getStatus().value());
        response.put("detail", ex.getDetail());
        response.put("instance", "/api/v1/auth/register");
        response.put("errors", ex.getErrors());
        return ResponseEntity.badRequest().body(response);
    }
}
