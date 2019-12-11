package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.Location;
import com.epam.repository.LocationRepository;

@Service
public class HomePageService {
	@Autowired
	LocationRepository locationRepository;

	public List<Location> getLocationList() {
		return locationRepository.findAll();
	}
}
