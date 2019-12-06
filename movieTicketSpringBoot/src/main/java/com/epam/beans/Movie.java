package com.epam.beans;

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
@Table(name = "movies")
@Getter
@Setter
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private int movieId;

	@ManyToOne(targetEntity = Location.class)
	@JoinColumn(name = "location_id")
	private Location location;

	@Column(name = "movie_name")
	private String movieName;

	@Column(name = "movie_lang")
	private String movieLanguage;

}
