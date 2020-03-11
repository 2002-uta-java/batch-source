package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger log = Logger.getRootLogger();

	@AfterReturning("within(com.revature.beans.*)")
	public void logAfterReturning(final JoinPoint jp) {
		log.info("method executed: " + jp.getSignature().getName());
	}

	@AfterThrowing(throwing = "e", pointcut = "within(com.revature.beans.*)")
	public void logAfterThrows(final JoinPoint jp, final Exception e) {
		log.warn(jp.getSignature().getName() + " threw an exception: " + e.getMessage());
	}
}
