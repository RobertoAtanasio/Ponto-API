package com.rapl.ponto.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/*
em PontoApiApplication.java injetar @EnableCaching
nos méodos de serviço, injetar @Cacheable("lancamentoPorId") e @CachePut("lancamentoPorId")
O @CachePut deixa em memória para uma posterior pesquisa
 */

@SpringBootApplication
@EnableCaching
public class PontoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PontoApiApplication.class, args);
	}

}
