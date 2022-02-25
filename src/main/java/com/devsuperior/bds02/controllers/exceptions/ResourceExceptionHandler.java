package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.exceptions.DataBaseException;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandarError> entityNotFound(ResourceNotFoundException entityNotFoundException,
			HttpServletRequest httpServletRequestF) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandarError standarError = test(entityNotFoundException, httpServletRequestF, status, "Resource not found");
		return ResponseEntity.status(status).body(standarError);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandarError> dataBase(DataBaseException dataBaseException,
			HttpServletRequest httpServletRequestF) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandarError standarError = test(dataBaseException, httpServletRequestF, status, "DataBase Exception");
		return ResponseEntity.status(status).body(standarError);
	}

	private StandarError test(Exception exception, HttpServletRequest httpServletRequestF, HttpStatus status,
			String message) {
		StandarError standarError = new StandarError();
		standarError.setTimestamp(Instant.now());
		standarError.setStatus(status.value());
		standarError.setError(message);
		standarError.setMessage(exception.getMessage());
		standarError.setPath(httpServletRequestF.getRequestURI());
		return standarError;
	}
}

