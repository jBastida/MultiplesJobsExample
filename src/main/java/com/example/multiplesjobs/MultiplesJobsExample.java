package com.example.multiplesjobs;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication // Contiene las etiquetas @ComponentScan,@EnableAutoConfiguration y @Configuration 
@EnableBatchProcessing // Necesaria para spring batch
@EnableScheduling //Necesaria para ejecutar los jobs más de una vez
public class MultiplesJobsExample {

	public static void main(String[] args) {
		SpringApplication.run(MultiplesJobsExample.class, args);
	}
}
