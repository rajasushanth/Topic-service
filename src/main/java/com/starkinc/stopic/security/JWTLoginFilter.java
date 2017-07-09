package com.starkinc.stopic.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starkinc.stopic.entity.TopicUser;
import com.starkinc.stopic.util.EncryptorUtil;

/**
 * @author RajaSushanth
 *
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	protected JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		TopicUser creds = new ObjectMapper().readValue(req.getInputStream(), TopicUser.class);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(creds.getUsername(), EncryptorUtil.getTextEncryptor().decrypt((creds.getPassword())), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		TokenAuthenticationService.addAuthentication(response, authResult.getName());
	}
	
}
