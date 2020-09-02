package es.spring.microservices.bankTransactionsManager.model.service;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransactionRequest {

	private String reference;
	
	private String accountIban;

	private Date date;
	
	private Float amount;
	
	private Float fee;
	
	private String description;
	
}