package com.diogo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.diogo.domain.service.RabbitService;

@Controller
public class ApiController {

	@Autowired
	RabbitService rabbitService;

	public void run() throws Exception{
		rabbitService.run();
	}
}
