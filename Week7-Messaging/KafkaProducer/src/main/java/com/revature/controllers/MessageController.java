package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.services.ProducerService;

@RestController
public class MessageController {
	
	@Autowired
	private ProducerService producerService;
	
	@PostMapping("/publish")
	public void sendMessage(@RequestParam("message")String message) {
		producerService.publicMessageToKafkaTopic(message);
	}

}
