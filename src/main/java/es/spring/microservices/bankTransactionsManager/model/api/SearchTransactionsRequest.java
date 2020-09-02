package es.spring.microservices.bankTransactionsManager.model.api;

import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTransactionsRequest {

	private SearchTransactionsFilter filter;
	private Direction direction;
	
}