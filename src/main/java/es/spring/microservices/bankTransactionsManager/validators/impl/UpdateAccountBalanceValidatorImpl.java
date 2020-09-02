package es.spring.microservices.bankTransactionsManager.validators.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.spring.microservices.bankTransactionsManager.model.service.Account;
import es.spring.microservices.bankTransactionsManager.validators.UpdateAccountBalanceValidator;

@Component
public class UpdateAccountBalanceValidatorImpl implements UpdateAccountBalanceValidator {

	@Override
	public List<String> validateAccountBalance(Account account, Float transactionAmount) {

		List<String> errors = new ArrayList<>();
		
		if (transactionAmount < 0) {
			// Validate account balance
			if (account.getBalance() + transactionAmount < 0) {
				errors.add("Account balance bellow zero is not allowed");
			}
		}

		return errors;
	}

}