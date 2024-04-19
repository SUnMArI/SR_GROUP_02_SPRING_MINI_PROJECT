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
    @ExceptionHandler(TimeoutOptCodeException.class)
    public ProblemDetail handleNotFoundException(TimeoutOptCodeException timeoutOptCodeException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.REQUEST_TIMEOUT,
                timeoutOptCodeException.getMessage()
        );
        problemDetail.setTitle("Timeout");
        problemDetail.setProperty("time", LocalDateTime.now());
        return problemDetail;
    }
    @ExceptionHandler(NoContentException.class)
    public ProblemDetail handleNotFoundException(NoContentException noContentException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NO_CONTENT,
                noContentException.getMessage()
        );
        problemDetail.setTitle("Timeout");
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
}
