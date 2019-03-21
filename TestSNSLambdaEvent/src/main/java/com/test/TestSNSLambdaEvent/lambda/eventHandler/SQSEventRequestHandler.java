package com.test.TestSNSLambdaEvent.lambda.eventHandler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class SQSEventRequestHandler extends SpringBootRequestHandler<SQSEvent, String> {
	
	public SQSEventRequestHandler() {
		super();
	}
	
	@Override
	protected Object convertEvent(SQSEvent event) {
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
