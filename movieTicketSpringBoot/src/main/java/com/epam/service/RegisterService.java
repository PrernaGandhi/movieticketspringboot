package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.User;
import com.epam.repository.UserRepository;

@Service
public class RegisterService {
	@Autowired
	UserRepository userRepo;

	public User registerUser(User user) {
		return userRepo.save(user);
	}
}
