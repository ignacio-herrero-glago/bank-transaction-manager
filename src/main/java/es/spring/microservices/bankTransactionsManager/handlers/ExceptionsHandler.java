package es.spring.microservices.bankTransactionsManager.handlers;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.spring.microservices.bankTransactionsManager.exceptions.NotFoundException;
import es.spring.microservices.bankTransactionsManager.exceptions.ValidationErrorException;
import es.spring.microservices.bankTransactionsManager.model.errors.CustomErrorResponse;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleNotFoundExceptions(NotFoundException ex) {

        CustomErrorResponse error = new CustomErrorResponse("NotFoundException", Arrays.asList(ex.getMessage()), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    
	}
	
	
	@ExceptionHandler(ValidationErrorException.class)
	public ResponseEntity<CustomErrorResponse> handleValidationErrorExceptions(ValidationErrorException ex) {

        CustomErrorResponse error = new CustomErrorResponse("ValidationErrorException", ex.getMessages(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}	
}