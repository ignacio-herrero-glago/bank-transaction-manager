package es.spring.microservices.bankTransactionsManager.model.service;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchTransactionsResponse {

	private List<Transaction> results = new ArrayList<>();
	
}