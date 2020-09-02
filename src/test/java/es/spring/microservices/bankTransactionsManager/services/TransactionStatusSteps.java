package es.spring.microservices.bankTransactionsManager.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import es.spring.microservices.bankTransactionsManager.configuration.OrikaConfiguration;
import es.spring.microservices.bankTransactionsManager.factories.TransactionFactory;
import es.spring.microservices.bankTransactionsManager.model.enums.Channel;
import es.spring.microservices.bankTransactionsManager.model.repository.Transaction;
import es.spring.microservices.bankTransactionsManager.model.service.TransactionStatusRequest;
import es.spring.microservices.bankTransactionsManager.model.service.TransactionStatusResponse;
import es.spring.microservices.bankTransactionsManager.repositories.TransactionRepository;
import es.spring.microservices.bankTransactionsManager.services.impl.TransactionServiceImpl;
import es.spring.microservices.bankTransactionsManager.validators.CreateTransactionValidator;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionStatusSteps {

	private static final PodamFactory factory = new PodamFactoryImpl();
	
	private Transaction transactionMocked;
	private TransactionStatusResponse transactionStatusResponse;
	
	private OrikaConfiguration mapper = new OrikaConfiguration();
	
	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private CreateTransactionValidator createTransactionValidator;

	@Mock
	private TransactionFactory transactionFactory;
	
	@Mock
	private AccountService accountService;
	
	private TransactionServiceImpl transactionService;

    @Before
    public void setUp() {
         MockitoAnnotations.initMocks(this);
         transactionService = new TransactionServiceImpl(mapper.defaultMapper(), transactionRepository, createTransactionValidator, transactionFactory, accountService);
    }
    
    /*****************************************/
	
    @Given("A transaction that is not stored in our system")
    public void a_transaction_that_is_not_stored_in_our_system() {
    	transactionMocked = null;
    }

    @Given("A transaction that is stored in our system")
    public void a_transaction_that_is_stored_in_our_system() {
    	transactionMocked = factory.manufacturePojo(Transaction.class);
    }

    @When("I check the status from channel {string}")
    public void i_check_the_status_from_channel(String channel) {
    	TransactionStatusRequest transactionStatusRequest = new TransactionStatusRequest("reference", Channel.valueOf(channel));
    	when(transactionRepository.findById(anyString())).thenReturn(Optional.ofNullable(transactionMocked));
    	transactionStatusResponse = transactionService.getTransactionStatus(transactionStatusRequest);
    }

    @Then("The system returns the status {string}")
    public void the_system_returns_the_status(String status) {
    	assertNotNull(transactionStatusResponse);
    	assertEquals(status, transactionStatusResponse.getStatus().name());
    }

    @And("the transaction date is today plus {int} days")
    public void the_transaction_date_is(int offset) throws ParseException {
    	ZonedDateTime today = ZonedDateTime.now();
    	transactionMocked.setDate(Date.from(today.plusDays(offset).toInstant()));
    }

    @And("the amount and the fee")
    public void and_the_amount_and_the_fee() {
    	assertNotNull(transactionStatusResponse.getAmount());
    	assertNotNull(transactionStatusResponse.getFee());
    }

    @And("the amount substracting the fee")
    public void the_amount_substracting_the_fee() {
    	assertNotNull(transactionStatusResponse.getAmount());
    	assertNotNull(transactionStatusResponse.getFee());
    	assertEquals(transactionMocked.getAmount() - transactionMocked.getFee(),
    			transactionStatusResponse.getAmount(),
    			0.001);    	
    }   
}
