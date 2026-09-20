package com.spring.SchoolManagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/students")
	public String students() {
		return "students";
	}
}