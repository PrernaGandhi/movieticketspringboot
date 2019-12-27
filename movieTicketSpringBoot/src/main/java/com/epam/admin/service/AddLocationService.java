package com.epam.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.Location;
import com.epam.repository.LocationRepository;

@Service
public class AddLocationService {
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	Location location;
	
	public Location addLocation(String locationName) {
		location.setLocationName(locationName);
		return locationRepository.save(location);
	}
}
