package com.epam.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.admin.service.AddMovieService;
import com.epam.beans.Location;
import com.epam.beans.Movie;
import com.epam.error.handler.EntryAlreadyExistsInDatabase;
import com.epam.rest.webservice.client.HomePageRestClient;

@Controller
public class AddMovieController {
	private static final String LOCATION_LIST = "locationList";
	@Autowired
	HomePageRestClient homePageRestClient;
	@Autowired
	AddMovieService addMovieService;

	@GetMapping("/displayMovie")
	public ModelAndView addMovie() {
		ModelAndView modelAndView = new ModelAndView();
		List<Location> locationList = homePageRestClient.getAllLocations();
		if (locationList != null && !locationList.isEmpty()) {
			modelAndView.addObject(LOCATION_LIST, locationList);
			modelAndView.setViewName("addMovie");
		} else {
			modelAndView.addObject("msg", "No locations found!!");
			modelAndView.setViewName("no-data");
		}
		return modelAndView;
	}

	@PostMapping("/addMovie")
	public ModelAndView addMovie(@RequestParam String locationSelected, @Validated Movie movie)
			throws EntryAlreadyExistsInDatabase {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Movie movieAdded = addMovieService.addMovie(movie, locationSelected);
			if (movieAdded != null) {
				modelAndView.setViewName("success");
			} else {
				modelAndView.setViewName("data-error");
			}
		} catch (Exception e) {
			if (e instanceof EntryAlreadyExistsInDatabase)
				throw new EntryAlreadyExistsInDatabase();
		}
		return modelAndView;
	}

	@ExceptionHandler(EntryAlreadyExistsInDatabase.class)
	public ModelAndView EntryAlreadyExistsInDatabase(HttpServletRequest request, Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", request.getRequestURL());
		modelAndView.setViewName("dup-data");
		return modelAndView;
	}
}
