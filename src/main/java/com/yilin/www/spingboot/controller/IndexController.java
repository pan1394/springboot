package com.yilin.www.spingboot.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yilin.www.spingboot.aop.SystemLog;

@RestController
public class IndexController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private HttpServletResponse resp;
	
    @SystemLog
	@GetMapping("/swagger")
	public void swagger() throws IOException {
		logger.info("logger added.");
		resp.sendRedirect("swagger/index.html"); 
		//return "swagger/index"; // not effects
		
	}
    
    @GetMapping("/swagger2")
	public void swagger2() throws IOException {
		logger.info("logger added.");
		resp.sendRedirect("swagger-ui.html"); 
		//return "swagger-ui";  //not effects
	}
}
