package com.epam.rest.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epam.beans.Seats;
import com.epam.beans.Theater;

@Service
public class RestClient {
	@Autowired
	RestTemplate restTemplate;

	public List<Seats> getSeatsList(String time, String date) {
		ResponseEntity<List<Seats>> resp = restTemplate.exchange("http://localhost:8080/seatsList/" + time + "/" + date,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Seats>>() {
				});

		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;

	}

	public List<Theater> getTheaterList(String movie) {
		ResponseEntity<List<Theater>> resp = restTemplate.exchange("http://localhost:8080/theaterList/" + movie,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Theater>>() {
				});
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;

	}

}
