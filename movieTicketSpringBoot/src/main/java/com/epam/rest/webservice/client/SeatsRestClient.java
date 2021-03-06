package com.epam.rest.webservice.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epam.beans.SeatType;
import com.epam.beans.Seats;
import com.epam.beans.UserOrders;

@Service
public class SeatsRestClient extends RestClientUtil {
	@Autowired
	RestTemplate restTemplate;

	public List<Seats> getSeatsList(String time, String date) {
		ResponseEntity<List<Seats>> resp = restTemplate.exchange(
				url.concat(port).concat("/seatsList/").concat(time).concat("/").concat(date), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Seats>>() {
				});
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;

	}

	public String getSelectedSeats(SeatType seatType) {
		ResponseEntity<String> resp = restTemplate.postForEntity(url.concat(port).concat("/getSeats/"), seatType,
				String.class);
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;

	}

	public UserOrders bookUserSeats(UserOrders order) {
		ResponseEntity<UserOrders> resp = restTemplate.postForEntity(url.concat(port).concat("/bookUserSeats/"), order,
				UserOrders.class);
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : null;

	}

	public boolean isSeatsSelected(String seatString) {
		ResponseEntity<Boolean> resp = restTemplate.exchange(
				url.concat(port).concat("/isSeatsSelected/").concat(seatString), HttpMethod.GET, null,
				new ParameterizedTypeReference<Boolean>() {
				});
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : false;

	}

	public double getTotalPrice(String seatString) {
		ResponseEntity<Double> resp = restTemplate.exchange(
				url.concat(port).concat("/getTotalPrice/").concat(seatString), HttpMethod.GET, null,
				new ParameterizedTypeReference<Double>() {
				});
		return resp.getStatusCode() == HttpStatus.FOUND ? resp.getBody() : 0.0;

	}
}
