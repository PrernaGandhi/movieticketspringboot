package com.epam.validator.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.Theater;
import com.epam.repository.TheaterRepository;
import com.epam.utils.RegexMatcher;

@Service
public class TheaterSelectedValidatorService implements ValidatorService {
	private static final String HYPHEN = "-";
	@Autowired
	RegexMatcher regexMatcher;
	@Autowired
	TheaterRepository theaterRepository;
	@Override
	public boolean validate(String inputValue) {
		boolean isValid = false;
		if (inputValue != null && inputValue.contains(HYPHEN)) {
			String theaterId = inputValue.split(HYPHEN)[0];
			String theaterName = inputValue.split(HYPHEN)[1];
			if (regexMatcher.matchPattern("([0-9]+)", theaterId)) {
				Optional<Theater> theater = theaterRepository.findById(Integer.parseInt(theaterId));
				String name = theater.isPresent()
						? theater.get().getTheaterName().concat("(").concat(theater.get().getScreenNumber()).concat(")")
						: "";
				if (name.equalsIgnoreCase(theaterName)) {
					isValid = true;
				}
			}
		}
		return isValid;
	}

}
