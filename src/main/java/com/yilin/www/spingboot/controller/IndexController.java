package com.yilin.www.spingboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yilin.www.spingboot.aop.SystemLog;

@RestController
public class IndexController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	 
    @SystemLog
	@GetMapping("/")
	public String index() {
		logger.info("logger added.");
		return "hello, I'm spring boot";
	}
}
