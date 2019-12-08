package com.epam.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table(name = "seats")
@Getter
@Setter
public class Seats {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "date")
	private Date date;
	@ManyToOne(targetEntity = Timings.class)
	@JoinColumn(name = "time_id")
	private Timings timings;
	@Column(name = "booked_seats")
	private String bookedSeats;
}
