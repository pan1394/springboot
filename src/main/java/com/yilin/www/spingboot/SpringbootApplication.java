package com.yilin.www.spingboot;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
 
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAsync
public class SpringbootApplication {
  
	@Bean("taskExecutor")
	public Executor executor() {
		Executor executor = Executors.newCachedThreadPool();
		return executor;
	}
   /*  
    @Bean(autowire = Autowire.BY_TYPE)
    public LogAspect logAspect() {
        return new LogAspect();
    }*/
  
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
