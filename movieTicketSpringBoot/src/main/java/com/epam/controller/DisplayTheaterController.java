package com.epam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.UserOrders;
import com.epam.rest.webservice.client.TheaterRestClient;
import com.epam.validator.service.MovieSelectedValidatorService;

@Controller
public class DisplayTheaterController {
	private static final String THEATER_LIST = "theaterList";
	private static final String ORDER = "order";
	private static final String MOVIE_SELECTED = "movieSelected";
	@Autowired
	TheaterRestClient theaterRestClient;
	@Autowired
	MovieSelectedValidatorService movieSelectedValidatorService;

	@GetMapping("/displayTheaters")
	public ModelAndView displayTheater(@RequestParam String movieSelected, HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		if (movieSelectedValidatorService.validate(movieSelected)) {
			String movieId = movieSelected.split("-")[0];
			String movieName = movieSelected.split("-")[1];
			httpSession.setAttribute(MOVIE_SELECTED, movieName);
			UserOrders userOrder = (UserOrders) httpSession.getAttribute(ORDER);
			userOrder.setMovieName(movieName);
			httpSession.setAttribute(ORDER, userOrder);
			httpSession.setAttribute(MOVIE_SELECTED, movieSelected);
			modelAndView.addObject(THEATER_LIST, theaterRestClient.getTheaterList(movieId));
			modelAndView.setViewName("displayTheaters");
		} else {
			modelAndView.setViewName("incorrect-url");
		}
		return modelAndView;
	}
}
