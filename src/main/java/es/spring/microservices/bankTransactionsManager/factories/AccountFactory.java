package es.spring.microservices.bankTransactionsManager.factories;

import es.spring.microservices.bankTransactionsManager.model.repository.Account;

public interface AccountFactory {

    public Account getAccount(String iban, Float balance);  

}