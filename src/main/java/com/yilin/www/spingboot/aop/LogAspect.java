package com.yilin.www.spingboot.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	   @Pointcut("@annotation(SystemLog)") 
	    public void annotationPointCut() {
		   logger.info("LogAspect loaded.");
	    }

	    

	    @After("annotationPointCut()")
	    public void after(JoinPoint joinPoint) {
	        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	        Method method = signature.getMethod();
	        SystemLog action = method.getAnnotation(SystemLog.class);
	        logger.info("注解方式拦截：方法名：{}" , method.getName());
	    }


	/*    @Before("execution(* com.tony.service.DemoMethodService.add())")
	    public void before(JoinPoint joinPoint) {
	        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	        Method method = signature.getMethod();
	        System.out.println("execution 拦截方式：" + method.getName());
	    }*/

}
