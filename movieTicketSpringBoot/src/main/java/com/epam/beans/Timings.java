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
@Table(name = "timings")
@Getter
@Setter
public class Timings {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "timings_id")
	private int timingsId;
	@ManyToOne(targetEntity = Theater.class)
	@JoinColumn(name = "theater_id")
	private Theater theater;
	private String timing;

}
