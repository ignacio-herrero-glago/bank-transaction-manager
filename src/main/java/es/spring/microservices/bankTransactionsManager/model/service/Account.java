package es.spring.microservices.bankTransactionsManager.model.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

	private String iban;

	private Float balance;
	
}