package es.spring.microservices.bankTransactionsManager.model.service;

import es.spring.microservices.bankTransactionsManager.model.enums.Channel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionStatusRequest {

	private String reference;
	
	private Channel channel;
	
}