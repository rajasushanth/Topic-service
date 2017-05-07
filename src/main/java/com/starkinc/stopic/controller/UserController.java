package com.starkinc.stopic.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starkinc.stopic.constants.Constants;
import com.starkinc.stopic.entity.TopicUser;
import com.starkinc.stopic.repository.UserRepository;
import com.starkinc.stopic.security.TokenAuthenticationService;
import com.starkinc.stopic.util.EncryptorUtil;
import com.starkinc.stopic.util.ServiceUtil;

@RestController
@RequestMapping(value = "/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class UserController {

	private UserRepository userRepository;
	private ResponseEntity<Object> invalidRequest;
	private ResponseEntity<Object> noContent;
	private ResponseEntity<Object> userAlreadyExist;
	private PasswordEncoder passwordEncoder;
	private String headerString;
	private String tokenPrefix;

	@RequestMapping(method = POST)
	public ResponseEntity<Object> saveOrUpdateUser(@RequestBody TopicUser user) {
		if (null != user && StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())) {
			if (!userRepository.exists(user.getUsername())) {
				ResponseEntity<Object> entity = save(user);
				addHeader(user.getUsername(), entity);
				return entity;
			} else {
				return userAlreadyExist;
			}
		} else {
			return invalidRequest;
		}

	}

	private void addHeader(String userName, ResponseEntity<Object> entity) {
		String JWT = TokenAuthenticationService.computeToken(userName);
		entity.getHeaders().add(headerString, tokenPrefix + " " + JWT);
	}

	private ResponseEntity<Object> save(TopicUser user) {
		String password = EncryptorUtil.getTextEncryptor().decrypt(user.getPassword());
		user.setPassword(passwordEncoder.encode(password));
		user.setEnabled(true);
		user.setRole(Constants.ROLE_USER);
		userRepository.save(user);
		ResponseEntity<Object> entity = ServiceUtil.buildEntity(CREATED, null);
		return entity;
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable("id") String id) {
		userRepository.delete(id);
		return noContent;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Resource
	public void setInvalidRequest(ResponseEntity<Object> invalidRequest) {
		this.invalidRequest = invalidRequest;
	}

	@Resource
	public void setNoContent(ResponseEntity<Object> noContent) {
		this.noContent = noContent;
	}

	@Resource
	public void setUserAlreadyExist(ResponseEntity<Object> userAlreadyExist) {
		this.userAlreadyExist = userAlreadyExist;
	}
	
	@Value(Constants.HEADER_STRING)
	public void setHeaderString(String headerString) {
		this.headerString = headerString;
	}
	
	@Value(Constants.TOKEN_PREFIX)
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	
	

}
