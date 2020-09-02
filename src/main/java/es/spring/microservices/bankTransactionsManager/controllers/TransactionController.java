package es.spring.microservices.bankTransactionsManager.controllers;

import static es.spring.microservices.bankTransactionsManager.controllers.TransactionController.TRANSACTIONS_MANAGER_PATH;
import static es.spring.microservices.bankTransactionsManager.controllers.TransactionController.TRANSACTIONS_MANAGER_TAG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.spring.microservices.bankTransactionsManager.configuration.SwaggerConfiguration;
import es.spring.microservices.bankTransactionsManager.model.api.CreateTransactionRequest;
import es.spring.microservices.bankTransactionsManager.model.api.CreateTransactionResponse;
import es.spring.microservices.bankTransactionsManager.model.api.SearchTransactionsRequest;
import es.spring.microservices.bankTransactionsManager.model.api.SearchTransactionsResponse;
import es.spring.microservices.bankTransactionsManager.model.api.TransactionStatusRequest;
import es.spring.microservices.bankTransactionsManager.model.api.TransactionStatusResponse;
import es.spring.microservices.bankTransactionsManager.services.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;

@RestController
@RequestMapping(value = SwaggerConfiguration.API_BASE_PATH + TRANSACTIONS_MANAGER_PATH)
@Api(tags = { TRANSACTIONS_MANAGER_TAG })
public class TransactionController {
	
	public static final String TRANSACTIONS_MANAGER_PATH = "/transactions-manager";
	public static final String TRANSACTIONS_MANAGER_TAG = "Transactions Manager";	

	private final MapperFacade mapper;
	private final TransactionService transactionService;
	
	@Autowired
	public TransactionController(MapperFacade mapper, TransactionService transactionService) {
		this.mapper = mapper;
		this.transactionService = transactionService;
	}

	/**
	 * Create Transaction
	 */
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "")
	@ApiOperation(value = "Create Transaction")
	public CreateTransactionResponse createTransaction(
			@ApiParam("The transaction creation request") @RequestBody CreateTransactionRequest createTransactionRequest) {
		return mapper.map(
				transactionService.createTransaction(mapper.map(createTransactionRequest, es.spring.microservices.bankTransactionsManager.model.service.CreateTransactionRequest.class)), 
				CreateTransactionResponse.class);
	}

	/**
	 * Search Transactions
	 */
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/search")
	@ApiOperation(value = "Search Transactions")
	public SearchTransactionsResponse searchTransactions(
			@ApiParam("The transaction search request") @RequestBody SearchTransactionsRequest searchTransactionsRequest) {
		return mapper.map(
				transactionService.searchTransactions(mapper.map(searchTransactionsRequest, es.spring.microservices.bankTransactionsManager.model.service.SearchTransactionsRequest.class)), 
				SearchTransactionsResponse.class);
	}
	
	
	/**
	 * Transactions Status
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/status")
	@ApiOperation(value = "Transactions Status")
	public TransactionStatusResponse getTransactionStatus(
			@ApiParam("The transaction status request") @RequestParam TransactionStatusRequest transactionStatusRequest) {
		return mapper.map(
				transactionService.getTransactionStatus(mapper.map(transactionStatusRequest, es.spring.microservices.bankTransactionsManager.model.service.TransactionStatusRequest.class)), 
				TransactionStatusResponse.class);
	}	
}