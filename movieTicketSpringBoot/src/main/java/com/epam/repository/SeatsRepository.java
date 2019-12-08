package com.epam.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epam.beans.Seats;

@Repository
public interface SeatsRepository extends CrudRepository<Seats, Integer> {

	@Query("select s from Seats s where s.timings.timingsId=:timingsId and s.date =:date")
	List<Seats> getAllBookedSeats(@Param("timingsId") int time, @Param("date") Date date);

}
