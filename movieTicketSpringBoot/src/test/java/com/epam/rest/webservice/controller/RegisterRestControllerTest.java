package com.epam.rest.webservice.controller;

import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.epam.beans.User;
import com.epam.service.RegisterService;

@SpringBootTest
class RegisterRestControllerTest {
	@Mock
	RegisterService registerService;
	@InjectMocks
	RegisterRestController registerRestController;
	@Mock
	User user;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_registerPositive() {
		doReturn(user).when(registerService).registerUser(user);
		ResponseEntity<User> responseEntity = registerRestController.register(user);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
	}

	@Test
	void test_registerNegative() {
		doReturn(null).when(registerService).registerUser(user);
		ResponseEntity<User> responseEntity = registerRestController.register(user);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
}
