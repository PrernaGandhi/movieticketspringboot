package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.Location;
import com.epam.rest.webservice.client.HomePageRestClient;

@Controller
public class HomePageController {
	private static final String LOCATION_LIST = "locationList";
	@Autowired
	HomePageRestClient homePageRestClient;

	@GetMapping(value = "/homePage")
	public ModelAndView getAllLocations() {
		ModelAndView modelAndView = new ModelAndView();
		List<Location> locationList = homePageRestClient.getAllLocations();
		modelAndView.addObject(LOCATION_LIST, locationList);
		modelAndView.setViewName("homePage");
		return modelAndView;
	}
}
