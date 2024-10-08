package br.com.beautique.ms_sync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://www.mongodb.com/try/download/compass
// mongodb://localhost:27017


// https://learn.microsoft.com/en-us/azure/architecture/patterns/cqrs
// https://martinfowler.com/bliki/CQRS.html
// https://medium.com/@marcelomg21/cqrs-command-query-responsibility-segregation-em-uma-arquitetura-de-micro-servi%C3%A7os-71dcb687a8a9

// https://www.eventstore.com/cqrs-pattern
// https://learn.microsoft.com/en-us/dotnet/architecture/microservices/microservice-ddd-cqrs-patterns/apply-simplified-microservice-cqrs-ddd-patterns

// https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp

@SpringBootApplication
public class MsSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSyncApplication.class, args);
	}

}
// Replication Microservice
// Microservico de Sincronização - Consumer da FILA --> Enviar MongoDB

// https://medium.com/@mertcakmak2/aws-standard-sqs-queue-with-spring-boot-974c163e0616
// https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-dead-letter-queues.html
// https://www.rabbitmq.com/tutorials/tutorial-three-spring-amqp
