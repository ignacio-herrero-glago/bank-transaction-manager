package es.spring.microservices.bankTransactionsManager.model.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {

	@Id
	@Column(name = "iban")
	private String iban;

	private Float balance;
	
}