package com.example.multiplesjobs.step.tasklets;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SimpleTasklet implements Tasklet, StepExecutionListener {

	private String stepName;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		stepName = stepExecution.getStepName();
	}
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("Tasklet running by " + stepName);
		return RepeatStatus.FINISHED;//Es necesario devolver un RepeatStatus.FINISHED para que no esté en continuo bucle 
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

}
