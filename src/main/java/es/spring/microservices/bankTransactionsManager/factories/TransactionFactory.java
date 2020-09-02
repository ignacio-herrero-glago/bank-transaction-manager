package es.spring.microservices.bankTransactionsManager.factories;

import es.spring.microservices.bankTransactionsManager.model.repository.Transaction;
import es.spring.microservices.bankTransactionsManager.model.service.CreateTransactionRequest;

public interface TransactionFactory {

    public Transaction getTransaction(CreateTransactionRequest createTransactionRequest);  

}