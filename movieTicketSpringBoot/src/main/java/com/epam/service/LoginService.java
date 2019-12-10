package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.epam.beans.Users;
import com.epam.repository.UserRepository;

@Controller
public class LoginService {

	@Autowired
	UserRepository userRepo;

	public Users loginUser(Users user) {
		return userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
