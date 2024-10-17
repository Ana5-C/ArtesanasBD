package com.artesanas.artesanasHueyapan.exception;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {
   /* @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?>handleException(NoSuchElementException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The information is not registered");
    }*/

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleException(NoSuchElementException e){
        return new ResponseEntity<>("The requested item is not registered", HttpStatus.NOT_FOUND);
    }


}
