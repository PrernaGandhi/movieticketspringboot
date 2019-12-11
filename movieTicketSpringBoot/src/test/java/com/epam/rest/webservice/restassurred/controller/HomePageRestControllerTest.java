package com.epam.rest.webservice.restassurred.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.epam.MovieTicketSpringBootApplication;
import com.epam.rest.webservice.client.RestClientUtil;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest
@ContextConfiguration(classes = MovieTicketSpringBootApplication.class)
class HomePageRestControllerTest {
	@Autowired
	@Qualifier(value = "restClientUtil")
	RestClientUtil urlDetails;


	@Test
	void test() {
		RestAssured.baseURI = urlDetails.url.concat(urlDetails.port).concat("/selectLocation");
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET);
		assertEquals(302, response.getStatusCode());
	}

}
