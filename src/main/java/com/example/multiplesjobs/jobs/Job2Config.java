package com.example.multiplesjobs.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.example.multiplesjobs.step.processors.Processor;
import com.example.multiplesjobs.step.readers.Reader;
import com.example.multiplesjobs.step.tasklets.SimpleTasklet;
import com.example.multiplesjobs.step.writers.Writer;

public class Job2Config {
	
	@Autowired
	StepBuilderFactory stepBuilder;
	
	@Autowired
	JobBuilderFactory jobBuilder;
	
	@Autowired
	SimpleTasklet simpleTasklet;

	@Autowired
	Reader reader;	
	
	@Autowired
	Processor processor;
	
	@Autowired
	Writer writer;
	
	@Bean
	private Step step2() {
		return stepBuilder.get("step2")
				.<Integer,String>chunk(5)//Cada cuanto se ejecuta el writer
	            .reader(reader)
	            .processor(processor)
	            .writer(writer)
	            .build();
	}
	
	@Bean
	private Step step3() {
		return stepBuilder.get("step3")
	            .tasklet(simpleTasklet)
	            .build();
	}
	
	@Bean
	private Job job2(Step step2, Step step3) {
		return jobBuilder
				.get("job2")
				.incrementer(new RunIdIncrementer())
				.flow(step2)
				.next(step3)
				.end()
				.build();
	}
	
}
