package com.example.demo.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type {@code ProductNotFoundException} and returns an error message.
     *
     * @param e the exception that was thrown; typically it is an instance of {@code ProductNotFoundException}
     * @return the message associated with the exception
     */
   @ExceptionHandler(ProductNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(Exception e){
        return e.getMessage();
   }

}
