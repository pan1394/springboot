package com.yilin.www.spingboot.controller;

import java.io.IOException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yilin.www.spingboot.aop.SystemLog;
import com.yilin.www.spingboot.service.AsyncService;
import com.yilin.www.spingboot.service.DatabaseService;
import com.yilin.www.spingboot.service.SpringELService;

@RestController
public class TestController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private AsyncService service;
	
	@Autowired
	private SpringELService elService;
	
	@Autowired
	private DatabaseService dbService;
	
    @SystemLog
	@GetMapping("/async")
	public String async() {
		try {
			service.asyncMethod();
		} catch (InterruptedException e) {
			 logger.error(e.getLocalizedMessage(),e);
		}
		return "Done";
	}
    
    @SystemLog
	@GetMapping("/springEL")
	public void springEL() {
    	try {
			elService.outputResource();
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(),e);
		} 
	}
    @GetMapping("/tables")
    public Set<String> listTables() {
    	logger.info("display all tables:");
    	dbService.allTables().forEach(table -> logger.info(table));
    	return dbService.allTables();
    }
}
