package com.epam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.beans.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer>{

	List<Location> findAll();
}
