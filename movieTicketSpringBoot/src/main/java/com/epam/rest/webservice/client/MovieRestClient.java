package com.epam.rest.webservice.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epam.beans.Movie;
import com.epam.utils.URLDetails;

@Service
public class MovieRestClient {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	URLDetails urlDetails;

	public List<Movie> getMovieForParticularLocation(String locationId) {
		ResponseEntity<List<Movie>> resp = restTemplate.exchange(
				urlDetails.url.concat(urlDetails.port).concat("/restMovie/").concat(locationId), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Movie>>() {
				});
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;

	}
}
