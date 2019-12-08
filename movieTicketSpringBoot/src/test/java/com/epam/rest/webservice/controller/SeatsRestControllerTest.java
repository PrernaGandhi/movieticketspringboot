package com.epam.rest.webservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.beans.SeatType;
import com.epam.beans.Seats;
import com.epam.beans.UserOrders;
import com.epam.service.BookSeatService;
import com.epam.service.DisplaySeatsService;

class SeatsRestControllerTest {
	@InjectMocks
	SeatsRestController restMovieController;
	@Mock
	DisplaySeatsService displaySeatsService;
	@Mock
	BookSeatService bookSeatService;
	@Mock
	List<Seats> seatsList;
	@Mock
	SeatType seatType;
	@Mock
	UserOrders order;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getSeatsListPositive() throws Exception {
		doReturn(seatsList).when(displaySeatsService).getSeatList("1", "2019-07-07");
		ResponseEntity<List<Seats>> responseEntity = restMovieController.getSeatsList("1", "2019-07-07");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_getSeatsListNegative() throws Exception {
		doReturn(null).when(displaySeatsService).getSeatList("1", "2019-07-07");
		ResponseEntity<List<Seats>> responseEntity = restMovieController.getSeatsList("1", "2019-07-07");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	void test_isSeatsSelectedPositive() throws Exception {
		doReturn(true).when(bookSeatService).isSeatsSelected(Mockito.anyString());
		ResponseEntity<Boolean> responseEntity = restMovieController.isSeatsSelected("N1-100");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_isSeatsSelectedNegative() throws Exception {
		doReturn(false).when(bookSeatService).isSeatsSelected(Mockito.anyString());
		ResponseEntity<Boolean> responseEntity = restMovieController.isSeatsSelected("N1-100");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	void test_getTotalPricePositive() throws Exception {
		doReturn(100.00).when(bookSeatService).getTotalPrice(Mockito.anyString());
		ResponseEntity<Double> responseEntity = restMovieController.getTotalPrice("N1-100");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_getTotalPriceNegative() throws Exception {
		doReturn(0.00).when(bookSeatService).getTotalPrice(Mockito.anyString());
		ResponseEntity<Double> responseEntity = restMovieController.getTotalPrice("N1-100");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	void test_getSeatsPositive() throws Exception {
		doReturn("").when(bookSeatService).getSelectedSeats(seatType);
		ResponseEntity<String> responseEntity = restMovieController.getSeats(seatType);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_getSeatsNegative() throws Exception {
		doReturn(null).when(bookSeatService).getSelectedSeats(seatType);
		ResponseEntity<String> responseEntity = restMovieController.getSeats(seatType);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	void test_bookUserSeatsPositive() throws Exception {
		doReturn(order).when(bookSeatService).bookUserSeats(order);
		ResponseEntity<UserOrders> responseEntity = restMovieController.bookUserSeats(order);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_bookUserSeatsNegative() throws Exception {
		doReturn(null).when(bookSeatService).bookUserSeats(order);
		ResponseEntity<UserOrders> responseEntity = restMovieController.bookUserSeats(order);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
