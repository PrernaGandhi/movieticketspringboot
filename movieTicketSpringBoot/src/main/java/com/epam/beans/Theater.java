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
@Table(name = "theater")
@Getter
@Setter
public class Theater {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "theater_id")
	private int theaterId;
	@ManyToOne(targetEntity = Movie.class)
	@JoinColumn(name = "movie_id")
	private Movie movie;
	@Column(name = "theater_name")
	private String theaterName;
	@Column(name = "screen_number")
	private String screenNumber;
}
