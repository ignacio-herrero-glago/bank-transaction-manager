package es.spring.microservices.bankTransactionsManager.model.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTransactionsFilter {

	private String accountIban;
	
}