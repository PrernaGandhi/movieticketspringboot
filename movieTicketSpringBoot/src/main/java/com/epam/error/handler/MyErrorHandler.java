package com.epam.error.handler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyErrorHandler implements ErrorController {

	@GetMapping("/error")
	public String handleError() {
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}