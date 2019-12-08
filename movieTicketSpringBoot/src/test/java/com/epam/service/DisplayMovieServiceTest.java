package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.beans.Movie;
import com.epam.repository.MovieRepository;

class DisplayMovieServiceTest {
	@Mock
	MovieRepository movieRepository;
	@InjectMocks
	DisplayMoviesService displayMovieService;
	Movie movie1 = new Movie();
	List<Movie> movieList = Arrays.asList(movie1);
	
	@BeforeEach
	void setup() {
		displayMovieService = new DisplayMoviesService();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getMovieList() {
		doReturn(movieList).when(movieRepository).findByLocation_locationId(1);
		assertEquals(movieList, displayMovieService.getMovieForParticularLocation("1"));
		verify(movieRepository).findByLocation_locationId(1);

	}

}
