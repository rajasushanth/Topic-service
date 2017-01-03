package com.starkinc.stopic.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLogger {
	
	@Pointcut("execution(* com.starkinc.stopic.*.User*.*(..))")
	public void user(){}
	
	@Around("user()")
	public Object logUser(ProceedingJoinPoint jp){
		Object o = null;
		try{
			System.out.println(jp.getSignature()+"::Enters");
			o = jp.proceed();
			System.out.println(jp.getSignature()+"::Exits");
			return o;
		}catch (Throwable e) {
			System.out.println(jp.getSignature()+"::Exception");
		}
		return o;
		
	}

}
