package com.epam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.Location;
import com.epam.repository.LocationRepository;

@Service
public class LocationService {
	private static List<Location> locationList = new ArrayList<>();
	@Autowired
	LocationRepository locationRepo;

	public List<Location> getAllLocations() {
		if (locationList.isEmpty()) {
			locationList = locationRepo.findAll();
		}
		return locationList;
	}
}
