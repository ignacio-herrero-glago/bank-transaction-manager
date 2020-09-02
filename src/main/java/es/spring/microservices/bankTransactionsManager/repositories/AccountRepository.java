package es.spring.microservices.bankTransactionsManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.spring.microservices.bankTransactionsManager.model.repository.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}