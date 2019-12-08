package com.epam.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.beans.SeatType;
import com.epam.beans.UserOrders;
import com.epam.rest.webservice.client.SeatsRestClient;

@SpringBootTest
@AutoConfigureMockMvc
class BookSeatsControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	SeatsRestClient seatsRestClient;

	@InjectMocks
	DisplaySeatsController displaySeatsController;

	@MockBean
	UserOrders userOrders;
	@MockBean
	SeatType seatType;

	String[] normalSeats = { "N1-100", "N2-100", "N3-100", "N4-100", "N5-100" };
	String[] premiumSeats = { "P1-200", "P2-200" };
	String[] royalSeats = { "R1-500" };

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_bookSeatsUserOrdersSaved() throws Exception {
		doReturn(userOrders).when(seatsRestClient).bookUserSeats(userOrders);
		doReturn("P1-200").when(seatsRestClient).getSelectedSeats(seatType);
		doReturn(100.00).when(seatsRestClient).getTotalPrice(Mockito.anyString());
		doReturn(true).when(seatsRestClient).isSeatsSelected(Mockito.anyString());
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/bookSeats").param("N", normalSeats)
				.sessionAttr("order", userOrders);
		mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		mockMvc.perform(builder).andExpect(view().name("bookingConfirmed"));
	}

	@Test
	void test_bookSeatsUserOrdersNotSaved() throws Exception {
		doReturn(null).when(seatsRestClient).bookUserSeats(userOrders);
		doReturn("P1-200").when(seatsRestClient).getSelectedSeats(seatType);
		doReturn(100.00).when(seatsRestClient).getTotalPrice(Mockito.anyString());
		doReturn(true).when(seatsRestClient).isSeatsSelected(Mockito.anyString());
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/bookSeats").param("N", normalSeats)
				.sessionAttr("order", userOrders);
		mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		mockMvc.perform(builder).andExpect(view().name("bookSeats"));
	}

	@Test
	void test_bookSeats_seatNotSelected() throws Exception {
		doReturn(null).when(seatsRestClient).bookUserSeats(userOrders);
		doReturn("P1-200").when(seatsRestClient).getSelectedSeats(seatType);
		doReturn(100.00).when(seatsRestClient).getTotalPrice(Mockito.anyString());
		doReturn(false).when(seatsRestClient).isSeatsSelected(Mockito.anyString());
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/bookSeats").param("N", normalSeats)
				.sessionAttr("order", userOrders);
		mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		mockMvc.perform(builder).andExpect(view().name("bookSeats"));
	}
}
