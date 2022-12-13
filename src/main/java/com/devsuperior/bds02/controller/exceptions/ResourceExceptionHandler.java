package com.devsuperior.bds02.controller.exceptions;

import com.devsuperior.bds02.services.exception.DataBaseException;
import com.devsuperior.bds02.services.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    /*
        // Sem contrutor costrutor:
        @ExceptionHandler(ResorceNotFoundException.class)
        public ResponseEntity<StanderdErrer> resourceNotFound(ResorceNotFoundException e, HttpServletRequest request){
            StanderdErrer err=new StanderdErrer();
            err.setTimestamp(Instant.now());
            err.setStatus(HttpStatus.NO_CONTENT.value());
            err.setError("Resource not found");
            err.setMessage(e.getMessage());
            err.setPath(request.getRequestURI());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }

     */


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StanderError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status= HttpStatus.NOT_FOUND;
        StanderError errer=new StanderError(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(errer);
    }




    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StanderError> dataBase(DataBaseException e, HttpServletRequest request){
        String error = "DataBase error";
        HttpStatus status= HttpStatus.BAD_REQUEST;
        StanderError errer=new StanderError(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(errer);
    }




}
