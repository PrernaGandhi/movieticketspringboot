package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.Theater;
import com.epam.repository.TheaterRepository;

@Service
public class DisplayTheaterService {
	@Autowired
	TheaterRepository theaterRepository;

	public List<Theater> getTheaterList(String movieId) {
		return theaterRepository.findByMovie_movieId(Integer.parseInt(movieId));
	}
}
