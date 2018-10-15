package com.example.multiplesjobs.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;

import com.example.multiplesjobs.step.tasklets.SimpleTasklet;

public class Job1Config {
	
	@Bean
	private Step step1(StepBuilderFactory stepBuilder, SimpleTasklet taskletEj) {
		return stepBuilder
				.get("step1") //Poner el mismo nombre que al método
	            .tasklet(taskletEj)
	            .build();
	}
	
	@Bean
	private Job job1(JobBuilderFactory jobBuilder, Step step1) {
		return jobBuilder
				.get("job1") //Poner el mismo nombre que al método
				.incrementer(new RunIdIncrementer())
				.flow(step1) //Primer step que se va a ejecutar
				.end()
				.build();
	}
	
}
