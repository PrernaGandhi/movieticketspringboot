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

import com.epam.beans.Theater;
import com.epam.rest.webservice.controller.TheaterRestController;
import com.epam.service.DisplayTheaterService;

class TheaterRestControllerTest {
	@Mock
	DisplayTheaterService displayTheaterService;
	@Mock
	List<Theater> theaterList;
	@InjectMocks
	TheaterRestController theaterRestController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getTheaterListPositive() throws Exception {
		doReturn(theaterList).when(displayTheaterService).getTheaterList("1");
		ResponseEntity<List<Theater>> responseEntity = theaterRestController.getTheaterList("1");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_getTheaterListNegative() throws Exception {
		doReturn(null).when(displayTheaterService).getTheaterList("1");
		ResponseEntity<List<Theater>> responseEntity = theaterRestController.getTheaterList("1");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
