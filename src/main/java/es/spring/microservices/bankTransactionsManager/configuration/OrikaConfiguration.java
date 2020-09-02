package es.spring.microservices.bankTransactionsManager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class OrikaConfiguration {

	@Bean
	public MapperFacade defaultMapper() {
		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		return factory.getMapperFacade();

	}
}