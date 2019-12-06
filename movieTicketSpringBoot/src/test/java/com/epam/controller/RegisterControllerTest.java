package com.epam.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.epam.beans.User;
import com.epam.rest.webservice.client.RegisterRestClient;

@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	RegisterRestClient registerRestClient;
	@MockBean
	User user;
	@InjectMocks
	RegisterController registerController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_displayUserRegistrationForm() throws Exception {
		mockMvc.perform(get("/registerUser")).andExpect(status().isOk()).andReturn();
		mockMvc.perform(get("/registerUser")).andExpect(view().name("register"));
	}

	@Test
	void test_register() throws Exception {
		doReturn(user).when(registerRestClient).register(user);
		mockMvc.perform(post("/registerUser")).andExpect(status().isFound()).andReturn();
		mockMvc.perform(post("/registerUser")).andExpect(redirectedUrl("/login"));

	}

}
