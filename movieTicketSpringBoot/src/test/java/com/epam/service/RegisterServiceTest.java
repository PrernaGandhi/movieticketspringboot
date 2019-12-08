package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.epam.beans.User;
import com.epam.repository.UserRepository;

public class RegisterServiceTest {

	@InjectMocks
	RegisterService registerService;
	@Spy
	UserRepository userRepository;
	@Mock
	User user;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_registerUser() {
		doReturn(user).when(userRepository).save(user);
		assertEquals(user, registerService.registerUser(user));
	}
}
