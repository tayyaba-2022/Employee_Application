package com.employee.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	Logger logger=LoggerFactory.getLogger(this.getClass());

	@Around("execution(* com.employee.*.*.*(..))")
	public Object logging(ProceedingJoinPoint pjp) throws Throwable{
		String methodName =  pjp.getSignature().getName();
		String className = pjp.getTarget().toString();
		logger.info("This is start logger for method:"+methodName+" inside class:"+className);
		Object obj=pjp.proceed();
		logger.info("This is end logger for method:"+methodName+" inside class:"+className);
		return obj;
		
	}
}
