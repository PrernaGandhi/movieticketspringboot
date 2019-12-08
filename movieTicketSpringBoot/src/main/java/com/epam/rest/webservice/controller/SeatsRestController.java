package com.epam.rest.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.beans.SeatType;
import com.epam.beans.Seats;
import com.epam.beans.UserOrders;
import com.epam.service.BookSeatService;
import com.epam.service.DisplaySeatsService;

@RestController
public class SeatsRestController {

	@Autowired
	DisplaySeatsService displaySeatsService;
	@Autowired
	BookSeatService bookSeatService;

	@GetMapping(value = "seatsList/{time}/{date}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Seats>> getSeatsList(@PathVariable String time, @PathVariable String date) {
		List<Seats> seatsList = displaySeatsService.getSeatList(time, date);
		ResponseEntity<List<Seats>> responseEntity = new ResponseEntity<>(seatsList, HttpStatus.FOUND);
		if (seatsList == null) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}

	@GetMapping(value = "isSeatsSelected/{seatsString}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> isSeatsSelected(@PathVariable String seatsString) {
		Boolean isSeatSelected = bookSeatService.isSeatsSelected(seatsString);
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(isSeatSelected, HttpStatus.FOUND);
		if (!isSeatSelected.booleanValue()) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}

	@GetMapping(value = "getTotalPrice/{seatsString}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Double> getTotalPrice(@PathVariable String seatsString) {
		Double totalPrice = bookSeatService.getTotalPrice(seatsString);
		ResponseEntity<Double> responseEntity = new ResponseEntity<>(totalPrice, HttpStatus.FOUND);
		if (Double.compare(totalPrice, 0) == 0) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}

	@PostMapping(value = "getSeats", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> getSeats(@RequestBody SeatType seatType) {
		String seatsString = bookSeatService.getSelectedSeats(seatType);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(seatsString, HttpStatus.FOUND);
		if (seatsString == null) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}

	@PostMapping(value = "bookUserSeats", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserOrders> bookUserSeats(@RequestBody UserOrders order) {
		order = bookSeatService.bookUserSeats(order);
		ResponseEntity<UserOrders> responseEntity = new ResponseEntity<>(order, HttpStatus.FOUND);
		if (order == null) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}
}