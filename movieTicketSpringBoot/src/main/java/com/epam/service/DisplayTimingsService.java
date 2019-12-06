package com.epam.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.beans.TheaterSeatingCapacity;
import com.epam.beans.Timings;
import com.epam.repository.TheaterCapacityRepository;
import com.epam.repository.TimingRepository;

@Repository
public class DisplayTimingsService {
	@Autowired
	TimingRepository timingRepository;
	@Autowired
	TheaterCapacityRepository theaterCapacityRepository;

	public List<Timings> getTimingsList(String theaterName) {
		return timingRepository.findByTheater_theaterId(Integer.parseInt(theaterName));
	}

	public List<TheaterSeatingCapacity> getTheaterSeatingCapacityList(String theaterName) {
		return theaterCapacityRepository.findByTheater_theaterId(Integer.parseInt(theaterName));
	}

	public Timings getTimings(int timeSelected) {
		return timingRepository.findById(timeSelected);
	}
}
