package com.epam.utils;

import java.util.Arrays;
import java.util.List;

import com.epam.beans.Seats;

public class HelperFunctions {
	public static boolean isCheckboxChecked(List<Seats> seatList, String seatId) {
		boolean isChecked = false;
		for (Seats seat : seatList) {
			List<String> bookedSeatList = Arrays.asList(seat.getBookedSeats().split(","));
			if (bookedSeatList.contains(seatId)) {
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
}
