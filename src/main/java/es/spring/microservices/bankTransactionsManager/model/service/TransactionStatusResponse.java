package es.spring.microservices.bankTransactionsManager.model.service;

import es.spring.microservices.bankTransactionsManager.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionStatusResponse {

	private String reference;
	
	private Status status;
	
	private Float amount;
	
	private Float fee;
	
}