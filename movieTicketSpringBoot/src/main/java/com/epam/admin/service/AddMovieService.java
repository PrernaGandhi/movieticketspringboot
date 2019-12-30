package com.epam.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.epam.beans.Location;
import com.epam.beans.Movie;
import com.epam.error.handler.EntryAlreadyExistsInDatabase;
import com.epam.repository.LocationRepository;
import com.epam.repository.MovieRepository;

@Service
public class AddMovieService {
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	Movie movie;

	public Movie addMovie(Movie movieAdded, String locationName) throws EntryAlreadyExistsInDatabase {
		Location location = locationRepository.findByLocationName(locationName);
		movie.setLocation(location);
		movie.setMovieName(movieAdded.getMovieName());
		movie.setMovieLanguage(movieAdded.getMovieLanguage());
		try {
			return movieRepository.save(movie);
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				throw new EntryAlreadyExistsInDatabase();
			}
		}
		return null;
	}

}
