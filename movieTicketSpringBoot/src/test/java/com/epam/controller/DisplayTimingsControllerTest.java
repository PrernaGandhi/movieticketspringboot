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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.epam.beans.TheaterSeatingCapacity;
import com.epam.beans.Timings;
import com.epam.beans.UserOrders;
import com.epam.rest.webservice.client.TimingsRestClient;

@SpringBootTest
@AutoConfigureMockMvc
class DisplayTimingsControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	TimingsRestClient timingsRestClient;

	@InjectMocks
	DisplayTimingsController displayTimingsController;

	@Mock
	List<Timings> timingsList;
	@MockBean
	UserOrders userOrders;

	@Mock
	List<TheaterSeatingCapacity> theaterCapacity;

	MockHttpSession httpSession;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_displayTimings() throws Exception {
		doReturn(timingsList).when(timingsRestClient).getTimingList("1");
		doReturn(theaterCapacity).when(timingsRestClient).getTheaterCapacity("1");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/displayTimings")
				.param("theaterSelected", "1").sessionAttr("theaterSelected", "1").sessionAttr("order", userOrders)
				.sessionAttr("theater", "AMB");
		mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		mockMvc.perform(builder).andExpect(view().name("displayTimings"));
	}

}
