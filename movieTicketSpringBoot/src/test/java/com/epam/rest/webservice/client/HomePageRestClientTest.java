package com.epam.rest.webservice.client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.epam.MovieTicketSpringBootApplication;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest
@ContextConfiguration(classes = MovieTicketSpringBootApplication.class)
class HomePageRestClientTest {
	final static String ROOT_URI = "http://localhost:8080/selectLocation";

	@Test
	void test() {
		RestAssured.baseURI = ROOT_URI;
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET);
		assertEquals(302, response.getStatusCode());
	}

}
