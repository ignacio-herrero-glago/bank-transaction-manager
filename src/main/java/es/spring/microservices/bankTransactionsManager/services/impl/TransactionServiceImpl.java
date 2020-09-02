package es.spring.microservices.bankTransactionsManager.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import es.spring.microservices.bankTransactionsManager.exceptions.ValidationErrorException;
import es.spring.microservices.bankTransactionsManager.factories.TransactionFactory;
import es.spring.microservices.bankTransactionsManager.model.enums.Channel;
import es.spring.microservices.bankTransactionsManager.model.enums.Status;
import es.spring.microservices.bankTransactionsManager.model.service.CreateTransactionRequest;
import es.spring.microservices.bankTransactionsManager.model.service.SearchTransactionsRequest;
import es.spring.microservices.bankTransactionsManager.model.service.SearchTransactionsResponse;
import es.spring.microservices.bankTransactionsManager.model.service.Transaction;
import es.spring.microservices.bankTransactionsManager.model.service.TransactionStatusRequest;
import es.spring.microservices.bankTransactionsManager.model.service.TransactionStatusResponse;
import es.spring.microservices.bankTransactionsManager.repositories.TransactionRepository;
import es.spring.microservices.bankTransactionsManager.services.AccountService;
import es.spring.microservices.bankTransactionsManager.services.TransactionService;
import es.spring.microservices.bankTransactionsManager.validators.CreateTransactionValidator;
import ma.glasnost.orika.MapperFacade;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final MapperFacade mapper;
	
	private final TransactionRepository transactionRepository;
	
	private final TransactionFactory transactionFactory;
	
	private final CreateTransactionValidator createTransactionValidator;
	
	private final AccountService accountService;
	

	@Autowired
	public TransactionServiceImpl(MapperFacade mapper, TransactionRepository transactionRepository, CreateTransactionValidator createTransactionValidator, TransactionFactory transactionFactory, AccountService accountService) {
		this.mapper = mapper;
		this.transactionRepository = transactionRepository;
		this.createTransactionValidator = createTransactionValidator;
		this.transactionFactory = transactionFactory;
		this.accountService = accountService;
	}

	@Override
	public Transaction createTransaction(CreateTransactionRequest createTransactionRequest) {

		// 1. Validations
		List<String> errors = createTransactionValidator.validateTransaction(createTransactionRequest);
		if (!CollectionUtils.isEmpty(errors)) {
			throw new ValidationErrorException(errors);
		}
		
		// 2. Create transaction and update account
		es.spring.microservices.bankTransactionsManager.model.repository.Transaction transaction = transactionRepository.save(transactionFactory.getTransaction(createTransactionRequest));
		accountService.updateAccountBalance(transaction.getAccountIban(), transaction.getAmount() - transaction.getFee());

		// 3. Return
		return mapper.map(transaction, Transaction.class);
		
	}

	@Override
	public TransactionStatusResponse getTransactionStatus(TransactionStatusRequest transactionStatusRequest) {


		// 1. Find the transaction
		Optional<es.spring.microservices.bankTransactionsManager.model.repository.Transaction> transactionOptional = transactionRepository.findById(transactionStatusRequest.getReference());
		
		// ". Build the response object
		TransactionStatusResponse transactionStatusResponse = new TransactionStatusResponse();
		transactionStatusResponse.setReference(transactionStatusRequest.getReference());

		if (transactionOptional.isEmpty()) {
			transactionStatusResponse.setStatus(Status.INVALID);	
		} else {
			es.spring.microservices.bankTransactionsManager.model.repository.Transaction transaction = transactionOptional.get();
			mapper.map(transaction, transactionStatusResponse);
			
			// Transaction date check
			Date today = new Date();
			if (DateUtils.isSameDay(transaction.getDate(), today)) {
			    // it's same
				transactionStatusResponse.setStatus(Status.PENDING);
			} else if (transaction.getDate().before(today)) {
			   // it's before
				transactionStatusResponse.setStatus(Status.SETTLED);
			} else {
			   // it's after
				if (Channel.INTERNAL.equals(transactionStatusRequest.getChannel()) ||
					Channel.CLIENT.equals(transactionStatusRequest.getChannel())) {
					transactionStatusResponse.setStatus(Status.FUTURE);
				} else {
					transactionStatusResponse.setStatus(Status.PENDING);
				}
			}
			
			// Channel check
			if (Channel.ATM.equals(transactionStatusRequest.getChannel()) ||
				Channel.CLIENT.equals(transactionStatusRequest.getChannel())) {
				transactionStatusResponse.setAmount(transaction.getAmount()-transaction.getFee());
			}
			
		}
		
		return transactionStatusResponse;
	}

	@Override
	public SearchTransactionsResponse searchTransactions(SearchTransactionsRequest searchTransactionsRequest) {

		// 1. Sort
		Sort sort = null;
		if (searchTransactionsRequest.getDirection() != null) {
			sort = Sort.by(searchTransactionsRequest.getDirection(), "amount");
		}
		
		// 2. Filter
		String accountIban = (searchTransactionsRequest != null && searchTransactionsRequest.getFilter() != null) ? searchTransactionsRequest.getFilter().getAccountIban() : null;
		
		// 3. Search
		List<es.spring.microservices.bankTransactionsManager.model.repository.Transaction> result = transactionRepository.findByAccountIban(accountIban, sort);
		
		return new SearchTransactionsResponse(mapper.mapAsList(result, Transaction.class));
		
	}

}