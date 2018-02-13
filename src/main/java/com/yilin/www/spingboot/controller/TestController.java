package com.yilin.www.spingboot.controller;

import java.io.IOException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yilin.www.spingboot.aop.SystemLog;
import com.yilin.www.spingboot.dto.ResultModel;
import com.yilin.www.spingboot.service.AsyncService;
import com.yilin.www.spingboot.service.DatabaseService;
import com.yilin.www.spingboot.service.SpringELService;

@RestController
@RequestMapping("/test")
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
    
    @GetMapping("/hello/{name}") 
	public ResponseEntity<ResultModel> helloWorld(@PathVariable String name){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		ResultModel body = ResultModel.ok(new StringBuilder("Hello, Welcome to spring boot ").append(name).toString()); 
		return new ResponseEntity<ResultModel>(body, HttpStatus.OK);
	}
	
}
