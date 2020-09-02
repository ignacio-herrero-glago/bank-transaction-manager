package es.spring.microservices.bankTransactionsManager.model.api;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransactionResponse {

	private String reference;
	
	@JsonProperty("account_iban")
	private String accountIban;

	private Date date;
	
	private Float amount;
	
	private Float fee;
	
	private String description;
	
}