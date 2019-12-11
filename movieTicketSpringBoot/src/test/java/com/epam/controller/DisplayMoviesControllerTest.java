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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.beans.Movie;
import com.epam.beans.UserOrders;
import com.epam.rest.webservice.client.MovieRestClient;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("spring")
class DisplayMoviesControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	MovieRestClient movieRestClient;

	@InjectMocks
	DisplayMoviesController displayMoviesController;

	@Mock
	List<Movie> movieList;
	@MockBean
	UserOrders userOrders;

	MockHttpSession httpSession;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_displayMovies() throws Exception {
		doReturn(movieList).when(movieRestClient).getMovieForParticularLocation("1");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/displayMovies")
				.param("locationSelected", "1-Hyderabad").sessionAttr("movieList", movieList)
				.sessionAttr("order", userOrders);
		mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		mockMvc.perform(builder).andExpect(view().name("displayMovies"));
	}
}
