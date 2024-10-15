package com.artesanas.artesanasHueyapan.exception;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?>handleException(NoSuchElementException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The information is not registered");
    }


}
