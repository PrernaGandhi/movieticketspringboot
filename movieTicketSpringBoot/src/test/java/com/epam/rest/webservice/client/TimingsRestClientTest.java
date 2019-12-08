package com.epam.rest.webservice.client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest
class TimingsRestClientTest {

	@Test
	void test_getTheaterCapacity() {
		RestAssured.baseURI = "http://localhost:8080/restTheaterCapacity/1";
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET);
		assertEquals(302, response.getStatusCode());
	}

	@Test
	void test_getTimingList() {
		RestAssured.baseURI = "http://localhost:8080/restTiming/1";
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET);
		assertEquals(302, response.getStatusCode());
	}

	@Test
	void test_getTimings() {
		RestAssured.baseURI = "http://localhost:8080/restGetTimings/1";
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET);
		assertEquals(302, response.getStatusCode());
	}
}
