package com.starkinc.stopic.aspects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.starkinc.stopic.constants.Constants;

/**
 * @author RajaSushanth
 *
 */
@Aspect
@Component
public class TopicControllerAspect {
	
	private Logger LOG = LoggerFactory.getLogger(TopicControllerAspect.class);
	
	@Around("execution(* com.starkinc.stopic.controller.TopicController.*(..))")
	public Object addRefeshedToken(ProceedingJoinPoint jp) throws Throwable{
		final Logger LOG = LoggerFactory.getLogger(jp.getTarget().getClass());
		Object o = null;
		try{
			LOG.info("Enters::"+jp.getSignature());
			o = jp.proceed();
			setTokenInHeader();
			LOG.info("Exits::"+jp.getSignature());
		}catch (Throwable e) {
			LOG.info("@Exception::"+jp.getSignature());
			LOG.info("@Cause::"+e.getMessage());
			throw e;
		}
		return o;
	}

	private void setTokenInHeader() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String token = (String) request.getAttribute(Constants.REFRESHED_TOKEN);
		if (StringUtils.isNoneBlank(token)) {
			LOG.info("***** ***** ***** Added refreshed tokens ***** ***** *****");
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
			response.addHeader(Constants.REFRESHED_TOKEN, token);
		}
	}

}
