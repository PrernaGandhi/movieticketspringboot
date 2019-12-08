package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.beans.Theater;
import com.epam.repository.TheaterRepository;

class DisplayTheaterServiceTest {
	@Mock
	TheaterRepository theaterRepository;
	@InjectMocks
	DisplayTheaterService displayTheaterService;
	@Mock
	List<Theater> theaterList ;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getTheaterList() {
		doReturn(theaterList).when(theaterRepository).findByMovie_movieId(1);
		assertEquals(theaterList, displayTheaterService.getTheaterList("1"));
		verify(theaterRepository).findByMovie_movieId(1);
	}

}
