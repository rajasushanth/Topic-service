package com.starkinc.stopic.security;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.starkinc.stopic.constants.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class TokenAuthenticationService {

	private static long timeIntervalInDays;
	private static String privateKey;
	private static String headerString;
	private static String tokenPrefix;

	public static void addAuthentication(HttpServletResponse res, String username) {
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(timeIntervalInDays)))
				.signWith(SignatureAlgorithm.HS512, "1235").compact();
		res.addHeader(headerString, tokenPrefix + " " + JWT);
	}

	public static Authentication getAuthentication(HttpServletRequest req) {
		String token = req.getHeader(headerString);
		if (null != token) {
			String user = Jwts.parser()
					.setSigningKey(privateKey)
					.parseClaimsJws(token.replace(tokenPrefix, ""))
					.getBody()
					.getSubject();
			return null != user ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
		}
		return null;

	}
	
	@Value(Constants.TIME_INTERVAL_IN_DAYS)
	public void setTimeIntervalInDays(long timeIntervalInDays) {
		TokenAuthenticationService.timeIntervalInDays = timeIntervalInDays;
	}
	
	@Value(Constants.PRIVATE_KEY)
	public void setPrivateKey(String privateKey) {
		TokenAuthenticationService.privateKey = privateKey;
	}
	
	@Value(Constants.HEADER_STRING)
	public void setHeaderString(String headerString) {
		TokenAuthenticationService.headerString = headerString;
	}
	
	@Value(Constants.TOKEN_PREFIX)
	public void setTokenPrefix(String tokenPrefix) {
		TokenAuthenticationService.tokenPrefix = tokenPrefix;
	}

	
}
