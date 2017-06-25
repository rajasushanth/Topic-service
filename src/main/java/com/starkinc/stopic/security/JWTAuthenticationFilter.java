package com.starkinc.stopic.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.starkinc.stopic.constants.Constants;

import io.jsonwebtoken.ExpiredJwtException;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		Authentication authentication = null;
		try {
			authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) req);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(req, res);
		} catch (ExpiredJwtException e) {
			((HttpServletResponse)res).sendError(HttpServletResponse.SC_GATEWAY_TIMEOUT, Constants.TOKEN_EXPIRED);
		}

	}

}
