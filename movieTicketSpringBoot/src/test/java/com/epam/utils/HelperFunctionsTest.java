package com.epam.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.beans.Seats;

class HelperFunctionsTest {
	Seats seat = new Seats();
	@Mock
	List<Seats> seatList;
	@InjectMocks
	HelperFunctions helperFunctions;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void test_isCheckboxCheckedPositive() {
		seat.setBookedSeats("N1,N2,N3,N4,N5");
		seatList = Arrays.asList(seat);
		assertEquals(true, HelperFunctions.isCheckboxChecked(seatList, "N1"));
	}
	@Test
	void test_isCheckboxCheckedNegative() {
		seat.setBookedSeats("N1,N2,N3,N4,N5");
		seatList = Arrays.asList(seat);
		assertEquals(false, HelperFunctions.isCheckboxChecked(seatList, "R1"));
	}

}
