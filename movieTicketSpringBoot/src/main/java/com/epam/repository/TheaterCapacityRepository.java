package com.epam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.beans.TheaterSeatingCapacity;

@Repository
public interface TheaterCapacityRepository extends CrudRepository<TheaterSeatingCapacity, Integer>{

	List<TheaterSeatingCapacity> findByTheater_theaterId(int parseInt);

}
