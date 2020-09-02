package es.spring.microservices.bankTransactionsManager.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTransactionsFilter {

	@JsonProperty("account_iban")
	private String accountIban;
	
}