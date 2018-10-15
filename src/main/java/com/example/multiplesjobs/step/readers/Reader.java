package com.example.multiplesjobs.step.readers;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class Reader implements ItemReader<Integer>,  StepExecutionListener{

	private List<Integer> list;
	private int count;
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		list = Arrays.asList( //Se debería de obtener la lista de elementos a procesar de un fichero o base de datos
			1,2,3,4,5
		);
		count = 0;
	}
	
	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Integer result = null;
		
		try {
			result = list.get(count++);//Obtenemos el valor de la lista de elementos
		}
		catch(IndexOutOfBoundsException ex) {
			// En caso de que salte esta excepción significa que no quedan elementos para leer 
			// y al no asignarle valor a result termina el readr
		}
		
		return result;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null; //Es necesario implementarlo aunque luego no se use y devuelve un null si usamos StepExecutionListener
	}

}
