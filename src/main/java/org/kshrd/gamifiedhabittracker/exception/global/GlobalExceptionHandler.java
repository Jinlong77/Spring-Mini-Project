package org.kshrd.gamifiedhabittracker.exception.global;

import jakarta.servlet.http.HttpServletRequest;
import org.kshrd.gamifiedhabittracker.exception.ApiException;
import org.kshrd.gamifiedhabittracker.exception.ResourceNotFoundException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.kshrd.gamifiedhabittracker.constant.Constant.API_URL;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = {
            ResourceNotFoundException.class,
            ApiException.class
    })
    public ProblemDetail handleResponseException(HttpServletRequest request, ResourceNotFoundException e){
        var detail = ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now());
        detail.setType(URI.create(API_URL + request.getRequestURI()));
        return detail;
    }

//    @ExceptionHandler()
//    public ProblemDetail handleApiException(HttpServletRequest request, ApiException e){
//        var detail = ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
//        detail.setProperty("timestamp", LocalDateTime.now());
//        detail.setType(URI.create(API_URL + request.getRequestURI()));
//        return detail;
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(HttpServletRequest request, MethodArgumentNotValidException ex) {
        var detail = ProblemDetail.forStatus(BAD_REQUEST);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        detail.setProperty("timestamp", LocalDateTime.now());
        detail.setProperty("errors", errors);
        detail.setType(URI.create(API_URL + request.getRequestURI() + "/validate"));
        return detail;
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleValidationExceptions(HttpServletRequest request, HandlerMethodValidationException ex) {
        var detail = ProblemDetail.forStatus(BAD_REQUEST);
        Map<String, String> errors = new HashMap<>();
        ex.getParameterValidationResults().forEach(
                parameterError -> {
                    String paramName = parameterError.getMethodParameter().getParameterName();

                    parameterError.getResolvableErrors().forEach(
                            resolvableError -> {
                                errors.put(paramName, resolvableError.getDefaultMessage());
                            }
                    );
                });
        detail.setProperty("timestamp", LocalDateTime.now());
        detail.setProperty("errors", errors);
        detail.setType(URI.create(API_URL + request.getRequestURI() + "/validate"));
        return detail;
    }
}
