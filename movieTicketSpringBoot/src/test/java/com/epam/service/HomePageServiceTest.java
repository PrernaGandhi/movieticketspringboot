package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.beans.Location;
import com.epam.repository.LocationRepository;

class HomePageServiceTest {
	@Mock
	LocationRepository locationRepository;
	@InjectMocks
	HomePageService homePageService;
	List<Location> locationList = new ArrayList<>();

	@BeforeEach
	void setup() {
		homePageService = new HomePageService();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getLocationList() {
		doReturn(locationList).when(locationRepository).findAll();
		assertEquals(locationList, homePageService.getLocationList());
		verify(locationRepository).findAll();
	}
}