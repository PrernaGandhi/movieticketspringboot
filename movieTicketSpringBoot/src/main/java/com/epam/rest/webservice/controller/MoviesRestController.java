package com.epam.rest.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.epam.beans.Movie;
import com.epam.service.DisplayMoviesService;

@RestController
public class MoviesRestController {
	@Autowired
	DisplayMoviesService displayMoviesService;

	@GetMapping(value = "restMovie/{locationId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Movie>> getMovieForParticularLocation(@PathVariable String locationId) {
		List<Movie> movieList = displayMoviesService.getMovieForParticularLocation(locationId);
		ResponseEntity<List<Movie>> responseEntity = new ResponseEntity<>(movieList, HttpStatus.FOUND);
		if (movieList == null || movieList.isEmpty()) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}
}
