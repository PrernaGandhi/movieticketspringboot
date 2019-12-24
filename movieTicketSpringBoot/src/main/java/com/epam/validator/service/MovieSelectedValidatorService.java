package com.epam.validator.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.Movie;
import com.epam.repository.MovieRepository;
import com.epam.utils.RegexMatcher;

@Service
public class MovieSelectedValidatorService implements ValidatorService {
	private static final String HYPHEN = "-";
	@Autowired
	RegexMatcher regexMatcher;
	@Autowired
	MovieRepository movieRepository;

	@Override
	public boolean validate(String inputValue) {
		boolean isValid = false;
		if (inputValue != null && inputValue.contains(HYPHEN)) {
			String movieId = inputValue.split(HYPHEN)[0];
			String movieName = inputValue.split(HYPHEN)[1];
			if (regexMatcher.matchPattern("([0-9]+)", movieId)) {
				Optional<Movie> movie = movieRepository.findById(Integer.parseInt(movieId));
				String name = movie.isPresent()
						? movie.get().getMovieName().concat("(").concat(movie.get().getMovieLanguage()).concat(")")
						: "";
				if (name.equalsIgnoreCase(movieName)) {
					isValid = true;
				}
			}
		}
		return isValid;
	}

}
