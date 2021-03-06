package com.epam.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.Timings;
import com.epam.beans.UserOrders;
import com.epam.rest.webservice.client.TimingsRestClient;
import com.epam.validator.service.DateSelectedValidatorService;

@Controller
public class DisplayTimingsController {
	private static final String DATE_SELECTED = "dateSelected";
	private static final String TIMINGS_LIST = "timingsList";
	private static final String ORDER = "order";
	@Autowired
	TimingsRestClient timingsRestClient;
	@Autowired
	DateSelectedValidatorService dateSelectedValidatorService;

	@GetMapping("/displayTimings")
	public ModelAndView displayTimings(@RequestParam Date dateSelected, HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		if (dateSelectedValidatorService.validate(dateSelected)) {
			UserOrders userOrder = (UserOrders) httpSession.getAttribute(ORDER);
			userOrder.setDateOfPurchase(dateSelected);
			httpSession.setAttribute(DATE_SELECTED, dateSelected);
			String theaterSelected = (String) httpSession.getAttribute("theaterSelected");
			List<Timings> timingsList = timingsRestClient.getTimingList(theaterSelected);
			if (timingsList != null && !timingsList.isEmpty()) {
				modelAndView.addObject(TIMINGS_LIST, timingsList);
				modelAndView.addObject(DATE_SELECTED, dateSelected);
				modelAndView.setViewName("displayTimings");
			} else {
				modelAndView.addObject("msg", "No timings found!!");
				modelAndView.setViewName("no-data");
			}
		} else {
			modelAndView.setViewName("incorrect-date");
		}
		return modelAndView;
	}
}
