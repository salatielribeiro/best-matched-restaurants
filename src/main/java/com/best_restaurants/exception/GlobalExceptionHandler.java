package com.best_restaurants.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgument(IllegalArgumentException ex) {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Invalid search criteria");
        pd.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String param = ex.getName();
        Class<?> expected = ex.getRequiredType();
        String value = String.valueOf(ex.getValue());

        String msg = "Parameter '%s' must be of type %s. Value '%s' is invalid."
                .formatted(param, expected != null ? expected.getSimpleName() : "unknown", value);

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Invalid parameter type");
        pd.setDetail(msg);
        return pd;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail onConstraintViolation(ConstraintViolationException ex) {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Validation error");
        pd.setDetail(ex.getMessage());
        return pd;
    }
}

