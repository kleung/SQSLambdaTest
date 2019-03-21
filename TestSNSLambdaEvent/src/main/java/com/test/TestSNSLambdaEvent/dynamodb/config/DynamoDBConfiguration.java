package com.test.TestSNSLambdaEvent.dynamodb.config;

import org.socialsignin.spring.data.dynamodb.mapping.DynamoDBMappingContext;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@EnableDynamoDBRepositories(
		basePackages= {"com.test.TestSNSLambdaEvent.dynamodb.repository"}
)
public class DynamoDBConfiguration {

	@Value("${amazon.aws.access.key}")
	protected String amazonAccessKey;
	
	@Value("${amazon.aws.secret.key}")
	protected String amazonSecretkey;
	
	@Value("${amazon.dynamodb.region}")
	protected String dynamoDbRegion;
	
	public DynamoDBConfiguration() {
		super();
	}
	
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(this.amazonAccessKey, this.amazonSecretkey);
	}
	
	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new AWSStaticCredentialsProvider(this.amazonAWSCredentials());
	}
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder.standard()
											.withCredentials(this.amazonAWSCredentialsProvider())
											.withRegion(this.dynamoDbRegion).build();
	}
	
	@Bean
	public DynamoDBMappingContext dynamoDBMappingContext() {
		return new DynamoDBMappingContext();
	}
}
