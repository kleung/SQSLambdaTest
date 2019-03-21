package com.test.TestSNSLambdaEvent.lambda.eventHandler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;

public class SNSEventRequestHandler extends SpringBootRequestHandler<SNSEvent, String> {

	public SNSEventRequestHandler() {
		super();
	}
	
	@Override
	protected Object convertEvent(SNSEvent event) {
		System.out.println("Event: " + event);
		System.out.println("Event class: " + event.getClass());
		Object result = event.getRecords();
		
		event.getRecords().forEach(
				(record) -> {
					System.out.println("Record Class: " + record.getClass());
				}
		);
		
		
		return result;
	}
}
