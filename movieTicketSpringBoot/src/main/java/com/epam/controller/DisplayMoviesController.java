package com.epam.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.epam.beans.Movie;
import com.epam.beans.UserOrders;
import com.epam.rest.webservice.client.MovieRestClient;
import com.epam.validator.service.LocationSelectedValidatorService;

@Controller
public class DisplayMoviesController {
	private static final String MOVIE_LIST = "movieList";
	private static final String ORDER = "order";
	@Autowired
	MovieRestClient movieRestClient;
	@Autowired
	LocationSelectedValidatorService locationSelectedValidatorService;

	@GetMapping("/displayMovies")
	public ModelAndView displayMovie(@RequestParam String locationSelected, HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		if (locationSelectedValidatorService.validate(locationSelected)) {
			String locationId = locationSelected.split("-")[0];
			String locationName = locationSelected.split("-")[1];
			UserOrders userOrder = (UserOrders) httpSession.getAttribute(ORDER);
			userOrder.setLocationName(locationName);
			httpSession.setAttribute(ORDER, userOrder);
			List<Movie> movieList = movieRestClient.getMovieForParticularLocation(locationId);
			if (movieList != null && !movieList.isEmpty()) {
				httpSession.setAttribute(MOVIE_LIST, movieList);
				modelAndView.setViewName("displayMovies");
			} else {
				modelAndView.addObject("msg", "No movies found!!");
				modelAndView.setViewName("no-data");
			}
		} else {
			modelAndView.setViewName("incorrect-url");
		}
		return modelAndView;
	}
}
