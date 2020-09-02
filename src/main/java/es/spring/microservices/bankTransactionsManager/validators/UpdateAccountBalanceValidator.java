package es.spring.microservices.bankTransactionsManager.validators;

import java.util.List;

import es.spring.microservices.bankTransactionsManager.model.service.Account;

public interface UpdateAccountBalanceValidator {

	public List<String> validateAccountBalance(Account account, Float transactionAmount);

}