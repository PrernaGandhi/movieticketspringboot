package com.epam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.SeatType;
import com.epam.beans.UserOrders;
import com.epam.rest.webservice.client.SeatsRestClient;

@Controller
public class BookSeatsController {
	private static final String EMPTY_STRING = "";
	private static final String REPLACE_PRICE = "(-[^,]+)";
	private static final String ORDER = "order";
	private static final String ROYALSEAT = "R";
	private static final String PREMIUMSEAT = "P";
	private static final String NORMALSEAT = "N";
	@Autowired
	SeatsRestClient seatsRestClient;
	@Autowired
	SeatType seatType;

	@PostMapping("/bookSeats")
	public ModelAndView bookSeats(HttpServletRequest req, HttpSession httpSession) {
		seatType.setNormalSeats(req.getParameterValues(NORMALSEAT));
		seatType.setRoyalSeats(req.getParameterValues(ROYALSEAT));
		seatType.setPremiumSeats(req.getParameterValues(PREMIUMSEAT));
		String seatsString = seatsRestClient.getSelectedSeats(seatType);
		ModelAndView modelAndView = new ModelAndView();
		if (seatsRestClient.isSeatsSelected(seatsString)) {
			double totalPrice = seatsRestClient.getTotalPrice(seatsString);
			UserOrders userOrder = (UserOrders) httpSession.getAttribute(ORDER);
			userOrder.setSeatsBooked(seatsString.replaceAll(REPLACE_PRICE, EMPTY_STRING));
			userOrder.setTotalPrice(totalPrice);
			httpSession.setAttribute(ORDER, userOrder);
			UserOrders userOrdersSaved = seatsRestClient.bookUserSeats(userOrder);
			if (userOrdersSaved != null) {
				modelAndView.setViewName("bookingConfirmed");
			}
		}
		return modelAndView;
	}
}