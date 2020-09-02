package es.spring.microservices.bankTransactionsManager.services;

import java.util.Optional;

import es.spring.microservices.bankTransactionsManager.model.service.Account;

public interface AccountService {

	public Optional<Account> findAccountByIban(String iban);

	public Account updateAccountBalance(String accountIban, Float transactionAmount);

}