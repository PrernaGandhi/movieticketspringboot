package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.epam.beans.Users;
import com.epam.repository.UserRepository;

class LoginServiceTest {
	@InjectMocks
	LoginService loginService;
	@Spy
	UserRepository userRepository;
	@Mock
	Users user;

	@BeforeEach
	void setup() {
		loginService = new LoginService();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_registerUser() {
		doReturn("").when(user).getUsername();
		doReturn("").when(user).getPassword();
		doReturn(user).when(userRepository).findByUsernameAndPassword("","");
		assertEquals(user, loginService.loginUser(user));
	}
}
