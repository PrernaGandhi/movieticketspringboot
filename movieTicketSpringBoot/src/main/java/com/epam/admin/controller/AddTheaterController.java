package com.epam.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.Location;
import com.epam.rest.webservice.client.HomePageRestClient;

@Controller
public class AddTheaterController {
	private static final String LOCATION_LIST = "locationList";
	@Autowired
	HomePageRestClient homePageRestClient;

	@GetMapping("/displayTheater")
	public ModelAndView displayTheater() {
		ModelAndView modelAndView = new ModelAndView();
		List<Location> locationList = homePageRestClient.getAllLocations();
		if (locationList != null && !locationList.isEmpty()) {
			modelAndView.addObject(LOCATION_LIST, locationList);
			modelAndView.setViewName("addTheater");
		} else {
			modelAndView.addObject("msg", "No locations found!!");
			modelAndView.setViewName("no-data");
		}
		return modelAndView;
	}
	
	@PostMapping("/addTheater")
	public ModelAndView addTheater() {
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
}
