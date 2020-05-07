package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

	@RequestMapping("/")
	public String welcome() {
		return "Welcome to first Spring Boot application";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "Hello from Spring Boot application";
	}
}
