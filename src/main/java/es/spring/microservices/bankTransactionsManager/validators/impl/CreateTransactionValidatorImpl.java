package es.spring.microservices.bankTransactionsManager.validators.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import es.spring.microservices.bankTransactionsManager.model.service.CreateTransactionRequest;
import es.spring.microservices.bankTransactionsManager.validators.CreateTransactionValidator;

@Component
public class CreateTransactionValidatorImpl implements CreateTransactionValidator {

	@Override
	public List<String> validateTransaction(CreateTransactionRequest createTransactionRequest) {

		List<String> errors = new ArrayList<>();
		
		// Required fields
		if (StringUtils.isEmpty(createTransactionRequest.getAccountIban())) {
			errors.add("'account_iban' is required");
		}
		if (StringUtils.isEmpty(createTransactionRequest.getAmount())) {
			errors.add("'amount' is required");
		}		

		return errors;
	}


}