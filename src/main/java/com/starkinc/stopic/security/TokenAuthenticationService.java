package com.starkinc.stopic.security;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	private static final long TIME_INTERVAL = 864_000_000;
	private static final String PRIVATE_KEY = "R9FGuY";
	private static final String HEADER_STRING = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer";

	public static void addAuthentication(HttpServletResponse res, String username) {
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + TIME_INTERVAL))
				.signWith(SignatureAlgorithm.HS512, PRIVATE_KEY).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	public static Authentication getAuthentication(HttpServletRequest req) {
		String token = req.getHeader(HEADER_STRING);
		if (null != token) {
			String user = Jwts.parser()
					.setSigningKey(PRIVATE_KEY)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			return null != user ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
		}
		return null;

	}

}
