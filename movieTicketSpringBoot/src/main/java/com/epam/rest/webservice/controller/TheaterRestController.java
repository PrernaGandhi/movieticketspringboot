package com.epam.rest.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.epam.beans.Theater;
import com.epam.service.DisplayTheaterService;

@RestController
public class TheaterRestController {
	@Autowired
	DisplayTheaterService displayTheaterService;

	@GetMapping(value = "restTheater/{movie}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Theater>> getTheaterList(@PathVariable String movie) {
		List<Theater> theaterList = displayTheaterService.getTheaterList(movie);
		ResponseEntity<List<Theater>> responseEntity = new ResponseEntity<>(theaterList, HttpStatus.FOUND);
		if (theaterList == null) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}
}
