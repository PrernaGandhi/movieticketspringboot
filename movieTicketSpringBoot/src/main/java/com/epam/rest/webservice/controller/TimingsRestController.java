package com.epam.rest.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.epam.beans.TheaterSeatingCapacity;
import com.epam.beans.Timings;
import com.epam.service.DisplayTimingsService;

@RestController
public class TimingsRestController {
	@Autowired
	DisplayTimingsService displayTimingsService;

	@GetMapping(value = "restTiming/{theater}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Timings>> getTimingList(@PathVariable String theater) {
		List<Timings> timingsList = displayTimingsService.getTimingsList(theater);
		ResponseEntity<List<Timings>> responseEntity = new ResponseEntity<>(timingsList, HttpStatus.FOUND);
		if (timingsList == null) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}

	@GetMapping(value = "restGetTimings/{time}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Timings> getTimings(@PathVariable int time) {
		Timings timings = displayTimingsService.getTimings(time);
		ResponseEntity<Timings> responseEntity = new ResponseEntity<>(timings, HttpStatus.FOUND);
		if (timings == null) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}

	@GetMapping(value = "restTheaterCapacity/{theater}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<TheaterSeatingCapacity>> getTheaterCapacity(@PathVariable String theater) {
		List<TheaterSeatingCapacity> capacityOfTheater = displayTimingsService.getTheaterSeatingCapacityList(theater);
		ResponseEntity<List<TheaterSeatingCapacity>> responseEntity = new ResponseEntity<>(capacityOfTheater,
				HttpStatus.FOUND);
		if (capacityOfTheater == null) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}
}
