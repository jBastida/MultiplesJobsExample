package com.example.multiplesjobs.step.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<Integer, String>{

	@Override
	public String process(Integer item) throws Exception {
		return item.toString();
	}

}
