package com.epam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.beans.Timings;

@Repository
public interface TimingRepository extends CrudRepository<Timings, Integer> {
	
	Timings findById(int timeId);

	List<Timings> findByTheater_theaterIdOrderByTiming(int parseInt);

}
