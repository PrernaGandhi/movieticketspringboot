package com.epam.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.beans.SeatType;

class BookSeatsHelperTest {
	String[] normalSeats = { "N1-100", "N2-100", "N3-100", "N4-100", "N5-100" };
	String[] premiumSeats = { "P1-200", "P2-200" };
	String[] royalSeats = { "R1-500" };
	BookSeatsHelper bookSeatsHelperClass;
	private static final String EMPTY_STRING = "";
	private static final String SEAT_STRING = "N1-100,N2-100,N3-100,N4-100,N5-100,R1-500";
	SeatType seatType;

	@BeforeEach
	void setup() {
		bookSeatsHelperClass = new BookSeatsHelper();
		seatType = new SeatType();
	}

	@Test
	void test_getSeatsSelectedContainingAllSeatTypes() {
		seatType.setNormalSeats(normalSeats);
		seatType.setPremiumSeats(premiumSeats);
		seatType.setRoyalSeats(royalSeats);
		String seatsString = bookSeatsHelperClass.getSeatsSelected(seatType);
		assertEquals("N1-100,N2-100,N3-100,N4-100,N5-100,P1-200,P2-200,R1-500", seatsString);
	}

	@Test
	void test_getSeatsSelectedContainingOneSeatTypes() {
		seatType.setNormalSeats(normalSeats);
		seatType.setPremiumSeats(null);
		seatType.setRoyalSeats(null);
		String seatsString = bookSeatsHelperClass.getSeatsSelected(seatType);
		assertEquals("N1-100,N2-100,N3-100,N4-100,N5-100", seatsString);
	}

	@Test
	void test_getSeatsSelectedContainingTwoSeatTypes() {
		seatType.setNormalSeats(normalSeats);
		seatType.setPremiumSeats(null);
		seatType.setRoyalSeats(royalSeats);
		String seatsString = bookSeatsHelperClass.getSeatsSelected(seatType);
		assertEquals(SEAT_STRING, seatsString);
	}

	@Test
	void test_getTotalPriceWithSeatString() {
		double totalPrice = bookSeatsHelperClass.getTotalPrice(SEAT_STRING);
		assertEquals(0, Double.compare(1000, totalPrice));
	}

	@Test
	void test_getTotalPriceWithEmptySeatString() {
		double totalPrice = bookSeatsHelperClass.getTotalPrice(EMPTY_STRING);
		assertEquals(0, Double.compare(0, totalPrice));
	}
}
