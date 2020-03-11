package com.revature.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class HelloWorldController {

	public String getHome() {
		return "home";
	}
}
