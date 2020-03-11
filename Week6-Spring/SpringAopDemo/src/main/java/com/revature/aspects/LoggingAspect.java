package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static Logger log = Logger.getRootLogger();

	@AfterReturning("within(com.revature.beans.*)")
	public void logAfterSuccess(JoinPoint jp) {
		log.info(jp.getSignature().getName()+" executed");
	}
	
	@AfterThrowing("within(com.revature.beans.*)")
	public void logAfter(JoinPoint jp) {
		log.warn(jp.getSignature().getName()+" executed with an exception");
	}
	
}
