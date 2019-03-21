package com.test.TestSNSLambdaEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.TestSNSLambdaEvent.dynamodb.config.DynamoDBConfiguration;
import com.test.TestSNSLambdaEvent.dynamodb.repository.TestDataRepository;
import com.test.TestSNSLambdaEvent.lambda.function.TestDataSNSEventConsumer;
import com.test.TestSNSLambdaEvent.lambda.function.TestDataSQSEventConsumer;

@SpringBootApplication
@Import(value= {DynamoDBConfiguration.class})
public class TestSnsLambdaEventApplication {

	@Autowired
	protected ObjectMapper mapper;
	
	@Autowired
	protected TestDataRepository testDataRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TestSnsLambdaEventApplication.class, args);
	}
	
	@Bean
	public TestDataSQSEventConsumer testDataSQSEventConsumer() {
		TestDataSQSEventConsumer result = new TestDataSQSEventConsumer();
		
		result.setMapper(this.mapper);
		result.setTestDataRepository(this.testDataRepository);
		
		return result;
	}
	
	@Bean
	public TestDataSNSEventConsumer testDataSNSEventConsumer() {
		TestDataSNSEventConsumer result = new TestDataSNSEventConsumer();
		
		result.setMapper(this.mapper);
		result.setTestDataRepository(this.testDataRepository);
		
		return result;
	}

}
