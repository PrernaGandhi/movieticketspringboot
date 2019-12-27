package com.epam.admin.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.admin.service.AddLocationService;
import com.epam.beans.Location;
import com.epam.error.handler.EntryAlreadyExistsInDatabase;

@Controller
public class AddLocationController {
	@Autowired
	AddLocationService addLocationService;

	@PostMapping("/addLocation")
	public ModelAndView addLocation(@RequestParam String location) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Location locationName = addLocationService.addLocation(location);
			if (locationName != null) {
				modelAndView.setViewName("success");
			} else {
				modelAndView.setViewName("data-error");
			}
		} catch (Exception e) {
			if (e instanceof SQLIntegrityConstraintViolationException)
				throw new EntryAlreadyExistsInDatabase();
		}
		return modelAndView;
	}

	@GetMapping("/displayLocation")
	public String addLocation() {
		return "addLocation";
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
