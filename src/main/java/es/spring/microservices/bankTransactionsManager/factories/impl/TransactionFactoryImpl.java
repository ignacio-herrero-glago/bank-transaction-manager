package es.spring.microservices.bankTransactionsManager.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.spring.microservices.bankTransactionsManager.factories.TransactionFactory;
import es.spring.microservices.bankTransactionsManager.model.repository.Transaction;
import es.spring.microservices.bankTransactionsManager.model.service.CreateTransactionRequest;
import ma.glasnost.orika.MapperFacade;

@Component
public class TransactionFactoryImpl implements TransactionFactory {

	private final MapperFacade mapper;
	
	@Autowired
	public TransactionFactoryImpl(MapperFacade mapper) {
		this.mapper = mapper;
	}	
	
	@Override
    public Transaction getTransaction(CreateTransactionRequest createTransactionRequest) {  

		Transaction transaction = mapper.map(createTransactionRequest, Transaction.class);
		
		if (transaction.getReference() == null) {
			transaction.setReference("reference-" + System.currentTimeMillis());
		}

		return transaction;  
	}  

}