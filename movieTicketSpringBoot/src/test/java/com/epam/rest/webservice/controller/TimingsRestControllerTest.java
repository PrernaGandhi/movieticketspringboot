package com.epam.rest.webservice.controller;

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

import com.epam.beans.TheaterSeatingCapacity;
import com.epam.beans.Timings;
import com.epam.rest.webservice.controller.TimingsRestController;
import com.epam.service.DisplayTimingsService;

class TimingsRestControllerTest {
	@Mock
	DisplayTimingsService displayTimingsService;
	@Mock
	List<TheaterSeatingCapacity> theaterSeatingList;
	@Mock
	List<Timings> timingsList;
	@Mock
	Timings timings;
	@InjectMocks
	TimingsRestController timingsRestController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getTimingListPositive() throws Exception {
		doReturn(timingsList).when(displayTimingsService).getTimingsList("1");
		ResponseEntity<List<Timings>> responseEntity = timingsRestController.getTimingList("1");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_getTimingListNegative() throws Exception {
		doReturn(null).when(displayTimingsService).getTimingsList("1");
		ResponseEntity<List<Timings>> responseEntity = timingsRestController.getTimingList("1");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	void test_getTheaterCapacityPositive() throws Exception {
		doReturn(theaterSeatingList).when(displayTimingsService).getTheaterSeatingCapacityList("1");
		ResponseEntity<List<TheaterSeatingCapacity>> responseEntity = timingsRestController.getTheaterCapacity("1");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_getTheaterCapacityNegative() throws Exception {
		doReturn(null).when(displayTimingsService).getTheaterSeatingCapacityList("1");
		ResponseEntity<List<TheaterSeatingCapacity>> responseEntity = timingsRestController.getTheaterCapacity("1");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	void test_getTimingsPositive() throws Exception {
		doReturn(timings).when(displayTimingsService).getTimings(1);
		ResponseEntity<Timings> responseEntity = timingsRestController.getTimings(1);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_getTimingsNegative() throws Exception {
		doReturn(null).when(displayTimingsService).getTimings(1);
		ResponseEntity<Timings> responseEntity = timingsRestController.getTimings(1);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
