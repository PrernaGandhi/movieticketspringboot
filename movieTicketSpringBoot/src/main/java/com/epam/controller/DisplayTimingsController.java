package com.epam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.UserOrders;
import com.epam.rest.webservice.client.TimingsRestClient;

@Controller
public class DisplayTimingsController {
	private static final String THEATER_CAPACITY = "theaterCapacity";
	private static final String TIMINGS_LIST = "timingsList";
	private static final String ORDER = "order";
	private static final String THEATER_SELECTED = "theaterSelected";
	private static final String THEATER = "theater";
	@Autowired
	TimingsRestClient timingsRestClient;

	@GetMapping("/displayTimings")
	protected ModelAndView displayTimings(@RequestParam String theaterSelected, HttpSession httpSession) {
		httpSession.setAttribute(THEATER_SELECTED, theaterSelected);
		UserOrders userOrder = (UserOrders) httpSession.getAttribute(ORDER);
		userOrder.setTheaterName((String) httpSession.getAttribute(THEATER));
		httpSession.setAttribute(ORDER, userOrder);
		httpSession.setAttribute(THEATER_CAPACITY, timingsRestClient.getTheaterCapacity(theaterSelected));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(TIMINGS_LIST, timingsRestClient.getTimingList(theaterSelected));
		modelAndView.setViewName("displayTimings");
		return modelAndView;
	}
}
