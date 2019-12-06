package com.epam.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.epam.beans.Location;
import com.epam.rest.webservice.client.HomePageRestClient;

@SpringBootTest
@AutoConfigureMockMvc
class HomePageControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	HomePageRestClient restClient;

	@InjectMocks
	HomePageController homePageController;

	@Mock
	List<Location> locationList;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getAllLocations() throws Exception {
		doReturn(locationList).when(restClient).getAllLocations();
		mockMvc.perform(post("/homePage")).andExpect(status().isOk()).andReturn();
		mockMvc.perform(post("/homePage")).andExpect(view().name("homePage"));
	}

}
