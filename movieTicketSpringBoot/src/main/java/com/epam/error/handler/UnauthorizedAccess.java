package com.epam.error.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UnauthorizedAccess {

	@GetMapping("/unauthorized")
	public String unauthorized() {
		return "unauthorized-access";
	}
}
