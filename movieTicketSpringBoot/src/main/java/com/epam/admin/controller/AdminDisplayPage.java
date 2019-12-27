package com.epam.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDisplayPage {

	@GetMapping("/admin")
	public String adminPage() {
		return "admin";
	}

	@GetMapping("/adminPage")
	public String displayPage() {
		return "admin-display-page";
	}
}
