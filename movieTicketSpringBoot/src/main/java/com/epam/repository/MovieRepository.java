package com.epam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epam.beans.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

	List<Movie> findByLocation_locationId(@Param("location_id") int locationId);

}
