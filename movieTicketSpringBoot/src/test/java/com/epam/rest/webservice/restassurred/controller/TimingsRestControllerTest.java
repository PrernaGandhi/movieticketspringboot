package com.epam.rest.webservice.restassurred.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.rest.webservice.client.RestClientUtil;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest
class TimingsRestControllerTest {
	@Autowired
	@Qualifier(value = "restClientUtil")
	RestClientUtil urlDetails;

	@Test
	void test_getTheaterCapacity() {
		RestAssured.baseURI = urlDetails.url.concat(urlDetails.port).concat("/restTheaterCapacity/").concat("1");
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET);
		assertEquals(302, response.getStatusCode());
	}

	@Test
	void test_getTimingList() {
		RestAssured.baseURI = urlDetails.url.concat(urlDetails.port).concat("/restTiming/").concat("1");
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET);
		assertEquals(302, response.getStatusCode());
	}

	@Test
	void test_getTimings() {
		RestAssured.baseURI = urlDetails.url.concat(urlDetails.port).concat("/restGetTimings/") + 1;
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET);
		assertEquals(302, response.getStatusCode());
	}
}
