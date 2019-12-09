package com.epam.utils;

public enum SeatTypeEnum {
	NORMALSEAT("N"),ROYALSEAT("R"),PLATINUMSEAT("P");
	private String seatType;
	
	SeatTypeEnum(String s) {
		seatType = s;
	}
	public String getSeatType() {
		return seatType;
	}
}
