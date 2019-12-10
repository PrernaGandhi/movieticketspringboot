package com.epam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.SeatType;
import com.epam.beans.UserOrders;
import com.epam.beans.Users;
import com.epam.rest.webservice.client.SeatsRestClient;
import com.epam.service.EmailMessage;
import com.epam.service.EmailService;
import com.epam.utils.SeatTypeEnum;

@Controller
public class BookSeatsController {
	private static final String BOOKING_CONFIRMATION = "BookingConfirmation";
	private static final String EMPTY_STRING = "";
	private static final String REPLACE_PRICE = "(-[^,]+)";
	private static final String ORDER = "order"; 
	@Autowired
	SeatsRestClient seatsRestClient;
	@Autowired
	EmailService emailService;
	@Autowired
	SeatType seatType;
	@Autowired
	EmailMessage emailMessage;

	@PostMapping("/bookSeats")
	public ModelAndView bookSeats(HttpServletRequest req, HttpSession httpSession) {
		seatType.setNormalSeats(req.getParameterValues(SeatTypeEnum.NORMALSEAT.getSeatType()));
		seatType.setRoyalSeats(req.getParameterValues(SeatTypeEnum.ROYALSEAT.getSeatType()));
		seatType.setPremiumSeats(req.getParameterValues(SeatTypeEnum.PLATINUMSEAT.getSeatType()));
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
				Users user = (Users) httpSession.getAttribute("userInfo");
				emailService.sendMail(user.getEmail(), BOOKING_CONFIRMATION, emailMessage.formatMsg(user, userOrdersSaved));
				modelAndView.setViewName("bookingConfirmed");
			}
		}
		return modelAndView;
	}
}