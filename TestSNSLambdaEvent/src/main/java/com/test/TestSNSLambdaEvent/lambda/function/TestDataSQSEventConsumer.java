package com.test.TestSNSLambdaEvent.lambda.function;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.TestSNSLambdaEvent.dynamodb.repository.TestDataRepository;
import com.test.TestSNSLambdaEvent.model.TestData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestDataSQSEventConsumer implements Function<Flux<SQSMessage>, Mono<String>> {
	
	private static Logger log = LoggerFactory.getLogger(TestDataSQSEventConsumer.class);
	
	protected TestDataRepository testDataRepository;
	
	protected ObjectMapper mapper;
	
	public TestDataSQSEventConsumer() {
		super();
	}

	public void setTestDataRepository(TestDataRepository testDataRepository) {
		this.testDataRepository = testDataRepository;
	}
	
	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	protected TestData readData(String content) {
		TestData result = null;
		
		try {
			result = this.mapper.readValue(content, TestData.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return result;
	}

	@Override
	public Mono<String> apply(Flux<SQSMessage> messages) {
		//throw new RuntimeException("Test failing!");
		messages.subscribe(
				(message) -> {
					//throw new RuntimeException("Test failing!");
					TestData record = this.readData(message.getBody());
					//throw new RuntimeException("Test failing!");
					this.testDataRepository.save(record);
				}
		);
		throw new RuntimeException("Test failing!");
		//return Mono.just("DONE");
	}

}
