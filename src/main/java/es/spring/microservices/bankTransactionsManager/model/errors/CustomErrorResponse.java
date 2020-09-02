package es.spring.microservices.bankTransactionsManager.model.errors;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomErrorResponse {

	private String code;
	
	private List<String> messages;
	
	private int status;
	
	private LocalDateTime timestamp;
	
	
}