package com.epam.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "theater_seating_capacity")
@Getter
@Setter
public class TheaterSeatingCapacity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne(targetEntity = Theater.class)
	@JoinColumn(name = "theater_id")
	private Theater theater;
	@Column(name = "category_of_seat")
	private String categoryOfSeat;
	@Column(name = "count_of_seats")
	private int countOfSeats;
	@Column(name = "price")
	private double priceOfSeat;
}
