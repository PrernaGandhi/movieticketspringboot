package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.Movie;
import com.epam.repository.MovieRepository;

@Service
public class DisplayMoviesService {
	@Autowired
	MovieRepository movieRepository;

	public List<Movie> getMovieForParticularLocation(String locationId) {
		return movieRepository.findByLocation_locationId(Integer.parseInt(locationId));
	}
}
