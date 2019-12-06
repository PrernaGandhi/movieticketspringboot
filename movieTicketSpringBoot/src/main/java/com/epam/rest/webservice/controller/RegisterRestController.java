package com.epam.rest.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.beans.User;
import com.epam.service.RegisterService;

@Controller
public class RegisterRestController {
	@Autowired
	RegisterService registerService;

	@PostMapping(value = "restRegister", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> register(@RequestBody User user) {
		user = registerService.registerUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.FOUND);
		if (user == null) {
			responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return responseEntity;
	}
}
