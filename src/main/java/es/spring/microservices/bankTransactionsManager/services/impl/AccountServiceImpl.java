package es.spring.microservices.bankTransactionsManager.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import es.spring.microservices.bankTransactionsManager.exceptions.ValidationErrorException;
import es.spring.microservices.bankTransactionsManager.factories.AccountFactory;
import es.spring.microservices.bankTransactionsManager.model.service.Account;
import es.spring.microservices.bankTransactionsManager.repositories.AccountRepository;
import es.spring.microservices.bankTransactionsManager.services.AccountService;
import es.spring.microservices.bankTransactionsManager.validators.UpdateAccountBalanceValidator;
import ma.glasnost.orika.MapperFacade;

@Service
public class AccountServiceImpl implements AccountService {

	private final MapperFacade mapper;
	
	private final AccountRepository accountRepository;
	
	private final UpdateAccountBalanceValidator updateAccountBalanceValidator;
	
	private final AccountFactory accountFactory;
	

	@Autowired
	public AccountServiceImpl(MapperFacade mapper, AccountRepository accountRepository, UpdateAccountBalanceValidator updateAccountBalanceValidator, AccountFactory accountFactory) {
		this.mapper = mapper;
		this.accountRepository = accountRepository;
		this.updateAccountBalanceValidator = updateAccountBalanceValidator;
		this.accountFactory = accountFactory;
	}

	@Override
	public Optional<Account> findAccountByIban(String iban) {
		return Optional.ofNullable(mapper.map(accountRepository.findById(iban).orElse(null), Account.class));
	}

	@Override
	public Account updateAccountBalance(String accountIban, Float transactionAmount) {

		// 1. Find the account
		Optional<Account> accountOptional = findAccountByIban(accountIban);
		
		Account account = null;
		if (accountOptional.isEmpty()) {
			// Create the new account
			account = mapper.map(accountFactory.getAccount(accountIban, 0f), Account.class);
		} else {
			account = accountOptional.get();
		}
		
		// 2. Validations
		List<String> errors = updateAccountBalanceValidator.validateAccountBalance(account, transactionAmount);
		if (!CollectionUtils.isEmpty(errors)) {
			throw new ValidationErrorException(errors);
		}
		
		// 3. Update the account balance
		account.setBalance(account.getBalance() + transactionAmount);
		
		return mapper.map(accountRepository.save(mapper.map(account, es.spring.microservices.bankTransactionsManager.model.repository.Account.class)), Account.class);
	}
	
}