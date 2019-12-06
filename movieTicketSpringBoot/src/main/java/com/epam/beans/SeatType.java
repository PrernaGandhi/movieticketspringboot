package com.epam.beans;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class SeatType {

	String[] normalSeats;
	String[] premiumSeats;
	String[] royalSeats;
}
