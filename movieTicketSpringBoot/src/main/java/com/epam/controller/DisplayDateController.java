package com.epam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.UserOrders;
import com.epam.rest.webservice.client.TimingsRestClient;
import com.epam.validator.service.TheaterSelectedValidatorService;

@Controller
public class DisplayDateController {
	private static final String THEATER_CAPACITY = "theaterCapacity";
	private static final String ORDER = "order";
	private static final String THEATER_SELECTED = "theaterSelected";
	@Autowired
	TimingsRestClient timingsRestClient;
	@Autowired
	TheaterSelectedValidatorService theaterSelectedValidatorService;

	@GetMapping("/displayDate")
	public ModelAndView displayTimings(@RequestParam String theaterSelected, HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		if (theaterSelectedValidatorService.validate(theaterSelected)) {
			String theaterId = theaterSelected.split("-")[0];
			String theaterName = theaterSelected.split("-")[1];
			httpSession.setAttribute(THEATER_SELECTED, theaterId);
			UserOrders userOrder = (UserOrders) httpSession.getAttribute(ORDER);
			userOrder.setTheaterName(theaterName);
			httpSession.setAttribute(ORDER, userOrder);
			httpSession.setAttribute(THEATER_CAPACITY, timingsRestClient.getTheaterCapacity(theaterId));
			modelAndView.setViewName("displayDate");
		} else {
			modelAndView.setViewName("incorrect-url");
		}
		return modelAndView;
	}
}
