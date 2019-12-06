package com.epam.rest.webservice.client;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

@SpringBootTest
class RegisterRestClientTest {
	final static String ROOT_URI = "http://localhost:8080/restRegister";

	@Test
	void test_register() {
		RestAssured.baseURI = ROOT_URI;
		RequestSpecification request = RestAssured.given();
		JSONObject params = new JSONObject();
		params.put("username", "admin" + (new Random().nextInt(10)));
		params.put("password", new BCryptPasswordEncoder().encode("admin11"));
		params.put("firstName", "admin11");
		params.put("lastName", "admin11");
		params.put("gender", "male");
		params.put("age", 22);
		request.header("Content-Type", "application/json");
		request.body(params.toJSONString());
		Response response = request.post(ROOT_URI);
		assertEquals(302, response.getStatusCode());
	}
}
