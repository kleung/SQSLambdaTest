package com.test.TestSNSLambdaEvent.dynamodb.repository;

import org.springframework.data.repository.CrudRepository;

import com.test.TestSNSLambdaEvent.model.TestData;

public interface TestDataRepository extends CrudRepository<TestData, String> {

}
