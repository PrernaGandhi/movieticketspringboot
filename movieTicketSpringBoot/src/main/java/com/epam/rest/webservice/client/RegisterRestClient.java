package com.epam.rest.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.epam.beans.User;
import com.epam.utils.URLDetails;

@Service
public class RegisterRestClient {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	URLDetails urlDetails;

	public User register(@RequestBody User user) {
		ResponseEntity<User> resp = restTemplate
				.postForEntity(urlDetails.url.concat(urlDetails.port).concat("/restRegister"), user, User.class);
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;
	}
}
