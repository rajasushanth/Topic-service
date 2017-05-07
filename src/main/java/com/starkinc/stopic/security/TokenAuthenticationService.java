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
import org.springframework.util.Base64Utils;

import com.starkinc.stopic.constants.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class TokenAuthenticationService {

	private static long timeInterval;
	private static String timeFactor;
	private static String privateKey;
	private static String headerString;
	private static String tokenPrefix;
	

	public static void addAuthentication(HttpServletResponse res, String username) {
		String JWT = computeToken(username);
		res.addHeader(headerString, tokenPrefix + " " + JWT);
	}

	public static String computeToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + resolveTimeInterval()))
				.signWith(SignatureAlgorithm.HS512, privateKey)
				.compact();
	}

	public static Authentication getAuthentication(HttpServletRequest req) {
		String token = req.getHeader(headerString);
		if (null != token) {
			Claims claims = Jwts.parser()
					.setSigningKey(privateKey)
					.parseClaimsJws(token.replace(tokenPrefix, ""))
					.getBody();
			String user = claims.getSubject();
			return null != user ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
		}
		return null;

	}
	
	
	private static String base64Encoder(String privateKey){
		return Base64Utils.encodeToString(privateKey.getBytes());
	}
	
	public static long resolveTimeInterval(){
		long timeInMillis = 0;
		switch (timeFactor) {
		case "seconds":
			timeInMillis = TimeUnit.SECONDS.toMillis(timeInterval);
			break;
		case "minutes":
			timeInMillis = TimeUnit.MINUTES.toMillis(timeInterval);
			break;
		case "hours":
			timeInMillis = TimeUnit.HOURS.toMillis(timeInterval);
			break;
		default:
			timeInMillis = TimeUnit.DAYS.toMillis(timeInterval);
			break;
		}
		return timeInMillis;
	}
	
	@Value(Constants.TIME_INTERVAL)
	public void setTimeInterval(long timeInterval) {
		TokenAuthenticationService.timeInterval = timeInterval;
	}
	
	@Value(Constants.PRIVATE_KEY)
	public void setPrivateKey(String privateKey) {
		TokenAuthenticationService.privateKey = base64Encoder(privateKey);
	}
	
	@Value(Constants.HEADER_STRING)
	public void setHeaderString(String headerString) {
		TokenAuthenticationService.headerString = headerString;
	}
	
	@Value(Constants.TOKEN_PREFIX)
	public void setTokenPrefix(String tokenPrefix) {
		TokenAuthenticationService.tokenPrefix = tokenPrefix;
	}
	
	@Value(Constants.TIME_FACTOR)
	public void setTimeFactor(String timeFactor) {
		TokenAuthenticationService.timeFactor = timeFactor;
	}
	
}
