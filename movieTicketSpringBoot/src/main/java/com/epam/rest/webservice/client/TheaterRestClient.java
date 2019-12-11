package com.epam.rest.webservice.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epam.beans.Theater;

@Service
public class TheaterRestClient extends RestClientUtil {
	@Autowired
	RestTemplate restTemplate;

	public List<Theater> getTheaterList(String movieSelected) {
		ResponseEntity<List<Theater>> resp = restTemplate.exchange(
				url.concat(port).concat("/restTheater/").concat(movieSelected), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Theater>>() {
				});
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;

	}
}
