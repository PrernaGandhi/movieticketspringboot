package com.epam.rest.webservice.client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

@SpringBootTest
class SeatsRestClientTest {
	String[] normalSeats = { "N1-100", "N2-100", "N3-100", "N4-100", "N5-100" };
	String[] premiumSeats = { "P1-200", "P2-200" };
	String[] royalSeats = { "R1-500" };

	@Test
	void test_getSelectedSeats() {
		RequestSpecification request = RestAssured.given();
		JSONObject params = new JSONObject();
		params.put("normalSeats", normalSeats);
		params.put("premiumSeats", premiumSeats);
		params.put("royalSeats", royalSeats);
		request.header("Content-Type", "application/json");
		request.body(params.toJSONString());
		Response response = request.post("http://localhost:8080/getSeats/");
		assertEquals(302, response.getStatusCode());
	}

	/*
	 * @Test void test_bookUserSeats() { RequestSpecification request =
	 * RestAssured.given(); JSONObject params = new JSONObject(); params.put("user",
	 * new User()); params.put("userName", ""); params.put("locationName", "");
	 * params.put("movieName", ""); params.put("theaterName", "");
	 * params.put("timings", new Timings()); params.put("seatsBooked", "");
	 * params.put("totalPrice", 100.00); params.put("date", ("2019-08-07"));
	 * request.header("Content-Type", "application/json");
	 * request.body(params.toJSONString()); Response response =
	 * request.post("http://localhost:8080/bookUserSeats/"); assertEquals(302,
	 * response.getStatusCode()); }
	 */

	@Test
	void test_getSeatsList() {
		RestAssured.baseURI = "http://localhost:8080/seatsList/1/2019-07-07";
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET);
		assertEquals(302, response.getStatusCode());
	}

	@Test
	void test_isSeatsSelected() {
		RestAssured.baseURI = "http://localhost:8080/isSeatsSelected/N1-100";
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET);
		assertEquals(302, response.getStatusCode());
	}

	@Test
	void test_getTotalPrice() {
		RestAssured.baseURI = "http://localhost:8080/getTotalPrice/N1-100";
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.GET);
		assertEquals(302, response.getStatusCode());
	}
}
