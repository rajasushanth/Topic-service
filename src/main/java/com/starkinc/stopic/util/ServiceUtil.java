package com.starkinc.stopic.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.starkinc.stopic.constants.Constants;
import com.starkinc.stopic.entity.TopicUser;
import com.starkinc.stopic.security.TokenAuthenticationService;

/**
 * @author RajaSushanth
 *
 */
public final class ServiceUtil {
	public static ResponseEntity<Object> buildEntity(HttpStatus status, Object entity) {
		return new ResponseEntity<Object>(entity, status);
	}
	
	public static void intializeSave(TopicUser user, PasswordEncoder passwordEncoder) {
		user.setPassword(decryptAndEncodePassword(user.getPassword(), passwordEncoder));
		user.setEnabled(true);
		user.setRole(Constants.ROLE_USER);
	}

	public static ResponseEntity<Object> addHeader(String username, String headerString, String tokenPrefix) {
		String JWT = TokenAuthenticationService.computeToken(username);
		ResponseEntity<Object> entity = ResponseEntity.status(HttpStatus.CREATED).header(headerString, tokenPrefix + " " + JWT).build();
		return entity;
	}
	
	public static String decryptAndEncodePassword(String password, PasswordEncoder passwordEncoder){
		String decyptedPassword = EncryptorUtil.getTextEncryptor().decrypt(password);
		return passwordEncoder.encode(decyptedPassword);
	}
	
	public static boolean isValidAnswer(String answer, String answerFromDB){
		return getParsedAnswer(answer).equals(getParsedAnswer(answerFromDB));
	}
	
	private static String getParsedAnswer(String answer){
		return answer.replaceAll(" ", "").toLowerCase();
	}

	private ServiceUtil() {
	}
}
