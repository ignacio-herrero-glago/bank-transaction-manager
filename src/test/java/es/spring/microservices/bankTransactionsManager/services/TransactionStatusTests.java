package es.spring.microservices.bankTransactionsManager.services;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/transactions/transaction-status.feature",
		plugin = {"pretty", "json:target/cucumber-report.json"},
		glue = "es.spring.microservices.bankTransactionsManager.services"
		)
public class TransactionStatusTests {

	

}
