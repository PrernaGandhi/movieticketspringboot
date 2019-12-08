package com.epam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.beans.Theater;

@Repository
public interface TheaterRepository extends CrudRepository<Theater, Integer>{

	List<Theater> findByMovie_movieId(Integer movieId);

}
