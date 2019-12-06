package com.epam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.beans.Timings;

@Repository
public interface TimingRepository extends CrudRepository<Timings, Integer> {

	List<Timings> findByTheater_theaterId(int parseInt);
	
	Timings findById(int timeId);

}
