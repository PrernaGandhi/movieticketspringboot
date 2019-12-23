package com.epam.service;

import org.springframework.stereotype.Service;

import com.epam.beans.UserOrders;
import com.epam.beans.Users;

@Service
public class EmailMessage {

	private static final String TAB = "\t";
	private static final String NEWLINE = "\r\n";
	StringBuilder msg;

	public String formatMsg(Users user,UserOrders userOrder) {
		msg = new StringBuilder();
		msg.append("Hi ")
		.append(user.getFirstName())
		.append(",").append(NEWLINE).append(TAB)
		.append("Your tickets have been confirmed!!").append(NEWLINE).append(TAB)
		.append("The details are as follows:").append(NEWLINE).append(TAB)
		.append("Order Id: ").append(userOrder.getOrderId()).append(NEWLINE).append(TAB)
		.append("Email Id: ").append(userOrder.getUser().getEmail()).append(NEWLINE).append(TAB)
		.append("Location: ").append(userOrder.getLocationName()).append(NEWLINE).append(TAB)
		.append("Theater Details: ").append(userOrder.getTheaterName()).append(NEWLINE).append(TAB)
		.append("Seats Booked: ").append(userOrder.getSeatsBooked()).append(NEWLINE).append(TAB)
		.append("Movie Deatils: ").append(userOrder.getMovieName()).append(NEWLINE).append(TAB)
		.append("Timings: ").append(userOrder.getTimings().getTiming()).append(NEWLINE).append(TAB)
		.append("Show Date: ").append(userOrder.getDateOfPurchase()).append(NEWLINE).append(TAB)
		.append("Total Price: ").append(userOrder.getTotalPrice()).append(NEWLINE).append(TAB)
		.append(NEWLINE).append(TAB)
		.append("This is your e-ticket.")
		.append(NEWLINE).append(TAB)
		.append("Enjoy The Experience!! Have a great time")
		.append(NEWLINE).append(NEWLINE)
		.append("Regards,").append(NEWLINE)
		.append("MovieBookingsDemoTeam.");
		return msg.toString();
	}
}
