package es.spring.microservices.bankTransactionsManager.model.api;

import es.spring.microservices.bankTransactionsManager.model.enums.Channel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionStatusRequest {

	private String reference;
	
	private Channel channel;
	
}