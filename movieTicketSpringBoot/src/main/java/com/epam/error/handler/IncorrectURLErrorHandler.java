package com.epam.error.handler;

import org.springframework.web.bind.annotation.GetMapping;

public class IncorrectURLErrorHandler {

	@GetMapping("/incorrect-url")
	public String incorrectUrl() {
		return "incorrect-url";
	}
	@GetMapping("/incorrect-date")
	public String incorrectDate() {
		return "incorrect-date";
	}
}
