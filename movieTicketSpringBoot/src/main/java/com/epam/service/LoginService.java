package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.epam.beans.User;
import com.epam.repository.UserRepository;

@Controller
public class LoginService {

	@Autowired
	UserRepository userRepo;

	public User loginUser(User user) {
		return userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
