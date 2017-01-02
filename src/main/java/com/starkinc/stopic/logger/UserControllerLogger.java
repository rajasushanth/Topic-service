package com.starkinc.stopic.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@ControllerAdvice(annotations = RestController.class)
public class UserControllerLogger extends AbstractJsonpResponseBodyAdvice  {
	
	@Pointcut("execution(* com.starkinc.stopic.controller.UserController.saveOrUpdateUser(..))")
	public void userController(){}
	
	@Around("userController()")
	public void logUserController(ProceedingJoinPoint jp){
		try{
			System.out.println("saveOrUpdate::Enters");
			jp.proceed();
			System.out.println("saveOrUpdate::Exits");
		}catch (Throwable e) {
			System.out.println("saveOrUpdate::Exception");
		}
		
	}

}
