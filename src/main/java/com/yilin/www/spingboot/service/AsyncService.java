package com.yilin.www.spingboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Async
	public void asyncMethod() throws InterruptedException {
		int sum = 0;
        for(int i=0;i<1000;i++){
            Thread.sleep(10);
            sum+= i;
        }
       logger.info("executeAsyncTask sum {} " , sum);
	}
}
