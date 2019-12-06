package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.beans.TheaterSeatingCapacity;
import com.epam.beans.Timings;
import com.epam.repository.TheaterCapacityRepository;
import com.epam.repository.TimingRepository;

class DisplayTimingsServiceTest {
	@Mock
	TimingRepository timingsRepository;
	@Mock
	TheaterCapacityRepository theaterCapacityRepository;
	@InjectMocks
	DisplayTimingsService displayTimingsService;
	@Mock
	List<Timings> timingsList;
	@Mock
	List<TheaterSeatingCapacity> theaterList;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getTimingsList() {
		doReturn(timingsList).when(timingsRepository).findByTheater_theaterId(1);
		assertEquals(timingsList, displayTimingsService.getTimingsList("1"));
		verify(timingsRepository).findByTheater_theaterId(1);
	}

	@Test
	void test_getTheaterSeatingCapacityList() {
		doReturn(theaterList).when(theaterCapacityRepository).findByTheater_theaterId(1);
		assertEquals(theaterList, displayTimingsService.getTheaterSeatingCapacityList("1"));
		verify(theaterCapacityRepository).findByTheater_theaterId(1);
	}

}
