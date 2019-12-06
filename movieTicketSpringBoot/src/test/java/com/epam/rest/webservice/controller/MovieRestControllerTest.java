package com.epam.rest.webservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.beans.Movie;
import com.epam.beans.User;
import com.epam.service.DisplayMoviesService;

class MovieRestControllerTest {
	@Mock
	DisplayMoviesService displayMoviesService;
	@InjectMocks
	MoviesRestController moviesRestController;
	@Mock
	User user;
	@Mock
	List<Movie> movieList;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getMovieForParticularLocationPositive() {
		doReturn(movieList).when(displayMoviesService).getMovieForParticularLocation("1");
		ResponseEntity<List<Movie>> responseEntity = moviesRestController.getMovieForParticularLocation("1");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_getMovieForParticularLocationNegative() {
		doReturn(null).when(displayMoviesService).getMovieForParticularLocation("1");
		ResponseEntity<List<Movie>> responseEntity = moviesRestController.getMovieForParticularLocation("1");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
}
