package es.spring.microservices.bankTransactionsManager.model.api;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

	private String reference;
	
	private String accountIban;

	private Date date;
	
	private Float amount;
	
	private Float fee;
	
	private String description;
	
}