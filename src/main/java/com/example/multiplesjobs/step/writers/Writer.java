package com.example.multiplesjobs.step.writers;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class Writer implements ItemWriter<String>{

	@Override
	public void write(List<? extends String> items) throws Exception {
		String result = items.stream()
				.map(Integer::parseInt) // Paso los strings a enteros usando el método parseInt de la clase Integer
				.filter(this::isEven) // Me quedo solo con los impares usando un método definido por mi en esta clase
				.map(i -> i.toString()) // Los vuelvo a pasar a string con una expresión lambda
				.collect(Collectors.joining(", ")); // Los concateno por ", " 
		System.out.println(
				MessageFormat.format("De una lista de cadenas con {0} elementos los números pares son [{1}].", 
						items.size(), 
						result)
				);
	}
	
	private Boolean isEven(Integer number) {
		return number % 2 == 0;
	}
}
