package com.epam.rest.webservice.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epam.beans.TheaterSeatingCapacity;
import com.epam.beans.Timings;
import com.epam.utils.URLDetails;

@Service
public class TimingsRestClient {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	URLDetails urlDetails;

	public List<TheaterSeatingCapacity> getTheaterCapacity(String theater) {
		ResponseEntity<List<TheaterSeatingCapacity>> resp = restTemplate.exchange(
				urlDetails.url.concat(urlDetails.port).concat("/restTheaterCapacity/").concat(theater), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<TheaterSeatingCapacity>>() {
				});
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;

	}

	public List<Timings> getTimingList(String theater) {
		ResponseEntity<List<Timings>> resp = restTemplate.exchange(
				urlDetails.url.concat(urlDetails.port).concat("/restTiming/").concat(theater), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Timings>>() {
				});
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;

	}

	public Timings getTimings(int timeId) {
		ResponseEntity<Timings> resp = restTemplate.getForEntity(urlDetails.url.concat(urlDetails.port).concat("/restGetTimings/") + timeId,
				Timings.class);
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;
	}

}
