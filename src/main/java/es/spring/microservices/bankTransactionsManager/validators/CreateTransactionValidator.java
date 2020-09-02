package es.spring.microservices.bankTransactionsManager.validators;

import java.util.List;

import es.spring.microservices.bankTransactionsManager.model.service.CreateTransactionRequest;

public interface CreateTransactionValidator {

	public List<String> validateTransaction(CreateTransactionRequest createTransactionRequest);

}