package com.epam.validator.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.Location;
import com.epam.repository.LocationRepository;
import com.epam.utils.RegexMatcher;

@Service
public class LocationSelectedValidatorService implements ValidatorService {
	private static final String HYPHEN = "-";
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	RegexMatcher regexMatcher;

	public boolean validate(String inputValue) {
		boolean isValid = false;
		if (inputValue != null && inputValue.contains(HYPHEN)) {
			String locationId = inputValue.split(HYPHEN)[0];
			String locationName = inputValue.split(HYPHEN)[1];
			if (regexMatcher.matchPattern("([0-9]+)", locationId)) {
				Optional<Location> location = locationRepository.findById(Integer.parseInt(locationId));
				String name = location.isPresent() ? location.get().getLocationName() : "";
				if (name.equalsIgnoreCase(locationName)) {
					isValid = true;
				}
			}
		}
		return isValid;
	}
}
