package es.spring.microservices.bankTransactionsManager.factories.impl;

import org.springframework.stereotype.Component;

import es.spring.microservices.bankTransactionsManager.factories.AccountFactory;
import es.spring.microservices.bankTransactionsManager.model.repository.Account;

@Component
public class AccountFactoryImpl implements AccountFactory {

	@Override
	public Account getAccount(String iban, Float balance) {

		Account account = new Account();
		account.setIban(iban);
		account.setBalance(balance);
		
		return account;
	}  

}