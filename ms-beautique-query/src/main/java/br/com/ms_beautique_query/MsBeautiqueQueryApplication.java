package br.com.ms_beautique_query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://docs.aws.amazon.com/prescriptive-guidance/latest/modernization-data-persistence/cqrs-pattern.html
// https://medium.com/design-microservices-architecture-with-patterns/cqrs-design-pattern-in-microservices-architectures-5d41e359768c

@SpringBootApplication
public class MsBeautiqueQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBeautiqueQueryApplication.class, args);
	}

}
// https://haithai91.medium.com/404-not-found-spring-boot-201657e15abe
// https://microservices.io/patterns/data/saga.html