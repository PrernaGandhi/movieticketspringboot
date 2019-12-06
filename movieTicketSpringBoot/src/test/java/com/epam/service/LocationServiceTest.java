package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.epam.beans.Location;
import com.epam.repository.LocationRepository;

class LocationServiceTest {

	@Spy
	LocationRepository locationRepository;
	@Mock
	List<Location> locationList;
	@InjectMocks
	LocationService locationService;

	@BeforeEach
	void setup() {
		locationService = new LocationService();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test() {
		doReturn(locationList).when(locationRepository).findAll();
		assertEquals(locationList, locationService.getAllLocations());
	}

}
