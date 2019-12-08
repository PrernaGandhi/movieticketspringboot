package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.beans.Seats;
import com.epam.repository.SeatsRepository;

class DisplaySeatsServiceTest {

	@Mock
	SeatsRepository seatsRepository;
	@InjectMocks
	DisplaySeatsService displaySeatsService;
	@Mock
	List<Seats> seatList;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getSeatList() {
		doReturn(seatList).when(seatsRepository).getAllBookedSeats(1, Date.valueOf("2019-04-12"));
		assertEquals(seatList, displaySeatsService.getSeatList("1", "2019-04-12"));
		verify(seatsRepository).getAllBookedSeats(1, Date.valueOf("2019-04-12"));
	}

}
