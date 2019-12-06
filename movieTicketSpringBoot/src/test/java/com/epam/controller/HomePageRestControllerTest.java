package com.epam.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.beans.Location;
import com.epam.rest.webservice.controller.HomePageRestController;
import com.epam.service.LocationService;

class HomePageRestControllerTest {
	@Mock
	List<Location> locationList;
	@Mock
	LocationService locationService;
	@InjectMocks
	HomePageRestController homePageRestController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getAllLocationsPositive() {
		doReturn(locationList).when(locationService).getAllLocations();
		ResponseEntity<List<Location>> responseEntity = homePageRestController.getAllLocations();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_getAllLocationsNegative() {
		doReturn(null).when(locationService).getAllLocations();
		ResponseEntity<List<Location>> responseEntity = homePageRestController.getAllLocations();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
}
