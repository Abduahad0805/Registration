package com.example.Users.exception;

import com.example.Users.exception.exceptions.NotFoundException;
import com.example.Users.exception.exceptions.NotSaveInDb;
import com.example.Users.exception.exceptions.UserFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Users.exception.classes.ErrorResponse;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHendller {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerExceprion(Exception ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFoundException(NotFoundException ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<ErrorResponse> handlerFoundUserException(UserFoundException ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NotSaveInDb.class)
    public ResponseEntity<ErrorResponse> handlerNotSaveInDbException(NotSaveInDb ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
