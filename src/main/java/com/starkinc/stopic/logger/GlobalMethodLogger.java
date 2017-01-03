package com.starkinc.stopic.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GlobalMethodLogger {
	
	@Pointcut("execution(* com.starkinc.stopic.*.*.*(..))")
	public void includedMethods(){}
	
	@Pointcut("! execution(* java.lang.reflect.Proxy+.*(..)) "
		 + "&& ! execution(* com.starkinc.stopic.configuration.JavaConfig.*(..))" 
		 + "&& ! execution(* com.starkinc.stopic.*.*.set*(..))")
	public void excludedMethods(){}
	
	@Around("includedMethods() && excludedMethods()")
	public Object logUser(ProceedingJoinPoint jp){
		final Logger LOG = LoggerFactory.getLogger(jp.getTarget().getClass());
		Object o = null;
		try{
			LOG.info("Enters::"+jp.getSignature());
			o = jp.proceed();
			LOG.info("Exits::"+jp.getSignature());
			return o;
		}catch (Throwable e) {
			LOG.info("Exception::"+jp.getSignature());
		}
		return o;
		
	}

}
