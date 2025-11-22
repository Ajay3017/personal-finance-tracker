package com.project.finance.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException ex){
        log.error("Bad input: {}", ex.getMessage());
        return new ResponseEntity<>("Bad input provided", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex){
        log.error("Not found exception: {}", ex.getMessage());
        return new ResponseEntity<>("Requested entity not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDBException(DataAccessException ex){
        log.error("DB error: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database error occurred");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex){
        log.error("Exception occurred: {}", ex.getMessage());
        return new ResponseEntity<>("Unexpected server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
