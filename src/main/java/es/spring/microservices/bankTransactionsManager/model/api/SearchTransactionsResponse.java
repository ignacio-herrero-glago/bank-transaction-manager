package es.spring.microservices.bankTransactionsManager.model.api;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTransactionsResponse {
	
	private List<Transaction> results = new ArrayList<>();

}