package com.epam.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.Timings;
import com.epam.beans.UserOrders;
import com.epam.beans.UserShowTime;
import com.epam.rest.webservice.client.SeatsRestClient;
import com.epam.rest.webservice.client.TimingsRestClient;

@Controller
public class DisplaySeatsController {
	private static final String SEATS_LIST = "seatsList";
	private static final String ORDER = "order";
	protected static final Logger LOGGER = LoggerFactory.getLogger(DisplaySeatsController.class);

	@Autowired
	SeatsRestClient restClient;
	@Autowired
	TimingsRestClient timingsRestClient;

	@GetMapping("/displaySeats")
	public ModelAndView displaySeats(@Validated UserShowTime showTime, HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			UserOrders userOrder = (UserOrders) httpSession.getAttribute(ORDER);
			Timings timings = timingsRestClient.getTimings(showTime.getTimeSelected());
			userOrder.setTimings(timings);
			userOrder.setTimingsId(userOrder.getTimings().getTimingsId());
			userOrder.setDateOfPurchase(showTime.getDateSelected());
			httpSession.setAttribute(ORDER, userOrder);
			modelAndView.addObject(SEATS_LIST, restClient.getSeatsList(String.valueOf(userOrder.getTimingsId()),
					String.valueOf(userOrder.getDateOfPurchase())));
			modelAndView.setViewName("displaySeats");
		} catch (NumberFormatException e) {
			LOGGER.error("Exception occurred while booking seat {}", e.getMessage());
		}
		return modelAndView;
	}

}
