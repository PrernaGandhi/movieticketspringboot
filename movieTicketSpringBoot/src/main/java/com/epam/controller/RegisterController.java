package com.epam.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.User;
import com.epam.repository.UserRepository;
import com.epam.rest.webservice.client.RegisterRestClient;

@Controller
public class RegisterController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
	@Autowired
	User user;
	@Autowired
	RegisterRestClient registerRestClient;
	@Autowired
	UserRepository userRepository;

	@PostMapping("/registerUser")
	public ModelAndView submitRegistrationForm(@Validated User userDetails) {
		String encrpytedPassword = new BCryptPasswordEncoder()
				.encode(userDetails.getPassword() != null ? userDetails.getPassword() : "");
		user.setUsername(userDetails.getUsername());
		user.setPassword(encrpytedPassword);
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setGender(userDetails.getGender());
		user.setAge(userDetails.getAge());
		ModelAndView modelAndView = new ModelAndView();
		Optional<User> userFound = userRepository.findByUsername(userDetails.getUsername());
		if (userFound.isPresent()) {
			modelAndView.addObject("errorMsg", "Username Already Taken");
			modelAndView.setViewName("register");
		} else {
			User userSaved = registerRestClient.register(user);
			if (userSaved != null) {
				LOGGER.info("User Registered!!!!");
				modelAndView.setViewName("redirect:/login");
			}
		}
		return modelAndView;
	}

	@GetMapping("/registerUser")
	public ModelAndView displayUserRegistrationForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}
}
