package com.epam.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.User;
import com.epam.beans.UserOrders;
import com.epam.repository.UserRepository;

@Controller
public class LoginController {
	private static final String USER_INFO = "userInfo";
	private static final String ORDER = "order";
	protected static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	UserRepository userRepository;

	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute User userDetails, HttpSession httpSession) {
		Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
		ModelAndView modelAndView = new ModelAndView();
		try {
			httpSession.setAttribute(USER_INFO, user.get());
			UserOrders userOrder = (UserOrders) httpSession.getAttribute(ORDER);
			if (userOrder == null) {
				userOrder = new UserOrders();
			}
			userOrder.setUser(user.get());
			userOrder.setUserName(user.get().getUsername());
			httpSession.setAttribute(ORDER, userOrder);
			modelAndView.setViewName("forward:/homePage");
		} catch (Exception e) {
			LOGGER.error("Exception occurred while logging in {} ", e.getMessage());
		}
		return modelAndView;
	}
}