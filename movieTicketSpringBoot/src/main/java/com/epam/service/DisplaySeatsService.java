package com.epam.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.Seats;
import com.epam.repository.SeatsRepository;

@Service
public class DisplaySeatsService {
	@Autowired
	SeatsRepository seatsRepository;

	public List<Seats> getSeatList(String time, String date) {
		return seatsRepository.getAllBookedSeats(Integer.parseInt(time), Date.valueOf(date));
	}
}
