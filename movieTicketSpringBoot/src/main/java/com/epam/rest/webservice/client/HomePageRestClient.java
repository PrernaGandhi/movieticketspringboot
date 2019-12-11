package com.epam.rest.webservice.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.epam.beans.Location;

@Service
public class HomePageRestClient extends RestClientUtil{
	@Autowired
	RestTemplate restTemplate;

	public List<Location> getAllLocations() {
		ResponseEntity<List<Location>> resp = restTemplate.exchange(
				url.concat(port).concat("/selectLocation"), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Location>>() {
				});
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;

	}
}
