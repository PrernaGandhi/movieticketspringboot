package com.epam.rest.webservice.restassurred.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.epam.rest.webservice.client.RestClientUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

@SpringBootTest
class RegisterRestControllerTest {
	@Autowired
	@Qualifier(value = "restClientUtil")
	RestClientUtil urlDetails;

	@Test
	void test_register() {
		RestAssured.baseURI = urlDetails.url.concat(urlDetails.port).concat("/restRegister");
		RequestSpecification request = RestAssured.given();
		JSONObject params = new JSONObject();
		params.put("username", "admin" + (new Random().nextInt(10)));
		params.put("password", new BCryptPasswordEncoder().encode("admin11"));
		params.put("firstName", "admin11");
		params.put("lastName", "admin11");
		params.put("email", "admin_" + (new Random().nextInt(10)) + "@epam.com");
		params.put("gender", "male");
		params.put("age", 22);
		request.header("Content-Type", "application/json");
		request.body(params.toJSONString());
		Response response = request.post(urlDetails.url.concat(urlDetails.port).concat("/restRegister"));
		assertEquals(302, response.getStatusCode());
	}
}
