package es.spring.microservices.bankTransactionsManager.model.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Transaction {

	@Id
	private String reference;
	
	@NotNull
	@Column(name = "account_iban")
	private String accountIban;

	private Date date;
	
	@NotNull
	private Float amount;
	
	private Float fee;
	
	private String description;
	
}