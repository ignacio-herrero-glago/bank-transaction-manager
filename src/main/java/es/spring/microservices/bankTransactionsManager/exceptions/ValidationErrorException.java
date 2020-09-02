package es.spring.microservices.bankTransactionsManager.exceptions;

import java.util.List;

import lombok.Getter;

@Getter
public class ValidationErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final List<String> messages;

	public ValidationErrorException(List<String> messages) {
		super(messages.toString());
		this.messages = messages;
	}
	
}