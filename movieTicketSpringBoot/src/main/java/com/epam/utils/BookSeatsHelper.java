package com.epam.utils;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.epam.beans.SeatType;

@Component
public class BookSeatsHelper {
	private static final String COMMA = ",";
	private static final String HYPHEN = "-";

	StringBuilder seatsString;

	public String getSeatsSelected(SeatType seatType) {
		seatsString = new StringBuilder();
		seatsString.append(Stream.of(seatType.getNormalSeats(), seatType.getPremiumSeats(),
				seatType.getRoyalSeats())
				.filter(this::hasSeats)
				.flatMap(Stream::of)
				.collect(Collectors.joining(COMMA)));

		return seatsString.substring(0, seatsString.length());
	}

	private boolean hasSeats(String[] seatsArray) {
		return seatsArray != null;
	}

	private boolean isSeatSelected(String seatsString) {
		return !seatsString.isEmpty();
	}

	public double getTotalPrice(String seatsString) {
		double totalPrice = 0.0;

		if (isSeatSelected(seatsString))
			totalPrice = Arrays.stream(seatsString.split(COMMA))
					.mapToDouble(s -> Double.parseDouble(s.split(HYPHEN)[1]))
					.sum();
		return totalPrice;
	}
}
