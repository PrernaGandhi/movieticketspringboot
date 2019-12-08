package com.epam.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.beans.Seats;
import com.epam.beans.Timings;
import com.epam.beans.UserOrders;
import com.epam.rest.webservice.client.SeatsRestClient;
import com.epam.rest.webservice.client.TimingsRestClient;

@SpringBootTest
@AutoConfigureMockMvc
class DisplaySeatsControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	SeatsRestClient seatsRestClient;

	@InjectMocks
	DisplaySeatsController displaySeatsController;

	@Mock
	List<Seats> seatsList;
	@MockBean
	TimingsRestClient timingsRestClient;
	@MockBean
	UserOrders userOrders;
	@MockBean
	Timings timings;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_displaySeats() throws Exception {
		doReturn(seatsList).when(seatsRestClient).getSeatsList("1", "2019-07-07");
		doReturn(timings).when(timingsRestClient).getTimings(1);
		doReturn(new Timings()).when(userOrders).getTimings();
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/displaySeats")
				.sessionAttr("timings", "8AM-10AM").sessionAttr("order", userOrders);
		mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		mockMvc.perform(builder).andExpect(view().name("displaySeats"));
	}

}
