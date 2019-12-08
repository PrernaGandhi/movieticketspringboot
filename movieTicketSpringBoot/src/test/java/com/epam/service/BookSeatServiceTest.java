package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.List;

import com.epam.beans.SeatType;
import com.epam.beans.Seats;
import com.epam.beans.UserOrders;
import com.epam.repository.SeatsRepository;
import com.epam.repository.UserOrderRepository;
import com.epam.utils.BookSeatsHelper;

class BookSeatServiceTest {
	@Mock
	UserOrderRepository userOrderRepository;
	@Spy
	BookSeatsHelper bookSeatHelperClass;
	@Mock
	UserOrders userOrder;
	@InjectMocks
	BookSeatService bookedSeatsService;
	@Mock
	SeatsRepository seatsRepository;
	@Spy
	Seats seat;
	@Spy
	List<Seats> seatList;

	String[] normalSeats = { "N1-100", "N2-100", "N3-100", "N4-100", "N5-100" };
	String[] premiumSeats = { "P1-200", "P2-200" };
	String[] royalSeats = { "R1-500" };

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_bookUserSeats() {
		doReturn(seatList).when(seatsRepository).getAllBookedSeats(1, Date.valueOf("2019-04-12"));
		doReturn(userOrder).when(userOrderRepository).save(userOrder);
		assertEquals(userOrder, bookedSeatsService.bookUserSeats(userOrder));
		verify(userOrderRepository).save(userOrder);
	}

	@Test
	void test_getSelectedSeats() {
		SeatType seatType = new SeatType();
		seatType.setNormalSeats(normalSeats);
		seatType.setPremiumSeats(premiumSeats);
		seatType.setRoyalSeats(royalSeats);
		assertEquals("N1-100,N2-100,N3-100,N4-100,N5-100,P1-200,P2-200,R1-500",
				bookedSeatsService.getSelectedSeats(seatType));
	}

	@Test
	void test_getTotalPrice() {
		assertEquals(0, Double.compare(
				bookedSeatsService.getTotalPrice("N1-100,N2-100,N3-100,N4-100,N5-100,P1-200,P2-200,R1-500"), 1400));
	}

	@Test
	void test_isSeatSelectedEmptyString() {
		assertEquals(false, bookedSeatsService.isSeatsSelected(""));
	}

	@Test
	void test_isSeatSelectedNullString() {
		assertEquals(false, bookedSeatsService.isSeatsSelected(null));
	}

	@Test
	void test_isSeatSelectedNonEmptyString() {
		assertEquals(true,
				bookedSeatsService.isSeatsSelected("N1-100,N2-100,N3-100,N4-100,N5-100,P1-200,P2-200,R1-500"));
	}

}
