package com.starkinc.stopic.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.starkinc.stopic.constants.Constants;
import com.starkinc.stopic.entity.TopicUser;
import com.starkinc.stopic.security.TokenAuthenticationService;

public final class ServiceUtil {
	public static ResponseEntity<Object> buildEntity(HttpStatus status, Object entity) {
		return new ResponseEntity<Object>(entity, status);
	}
	
	public static void intializeSave(TopicUser user, PasswordEncoder passwordEncoder) {
		String password = EncryptorUtil.getTextEncryptor().decrypt(user.getPassword());
		user.setPassword(passwordEncoder.encode(password));
		user.setEnabled(true);
		user.setRole(Constants.ROLE_USER);
	}

	public static ResponseEntity<Object> addHeader(String username, String headerString, String tokenPrefix) {
		String JWT = TokenAuthenticationService.computeToken(username);
		ResponseEntity<Object> entity = ResponseEntity.status(HttpStatus.CREATED).header(headerString, tokenPrefix + " " + JWT).build();
		return entity;
	}

	private ServiceUtil() {
	}
}
