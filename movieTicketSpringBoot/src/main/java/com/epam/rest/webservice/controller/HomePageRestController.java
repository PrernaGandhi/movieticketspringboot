package com.epam.rest.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.beans.Location;
import com.epam.service.LocationService;

@RestController
public class HomePageRestController {
	@Autowired
	LocationService locationService;
	@GetMapping(value = "selectLocation", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Location>> getAllLocations() {
		List<Location> locationList = locationService.getAllLocations();
		ResponseEntity<List<Location>> responseEntity = new ResponseEntity<>(locationList, HttpStatus.FOUND);
		if (locationList == null || locationList.isEmpty()) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}

}
