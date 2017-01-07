package com.starkinc.stopic.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starkinc.stopic.dao.UserDAO;
import com.starkinc.stopic.entity.User;
import com.starkinc.stopic.util.ServiceUtil;

@RestController
@RequestMapping(value = "/users",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	private UserDAO userDAO;
	private ResponseEntity<Object> noRecordFound;
	private ResponseEntity<Object> invalidRequest;
	private ResponseEntity<Object> noContent;
	
	@PostMapping
	public ResponseEntity<Object> saveOrUpdateUser(@RequestBody User user){
		if(null != user && StringUtils.isNotBlank(user.getName()) 
				&& StringUtils.isNotBlank(user.getPassword())){
			return ServiceUtil.buildEntity(HttpStatus.CREATED, userDAO.saveOrUpdate(user)) ;
		}else{
			return invalidRequest;
		}
		
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Object> findUserById(@PathVariable("id") String id){
		User user = userDAO.findOne(id);
		if(null != user){
			return ServiceUtil.buildEntity(HttpStatus.FOUND, user);
		}else{
			return noRecordFound;
		}
	}
	
	@GetMapping(value="/search")
	public ResponseEntity<Object> findUserByName(@RequestParam("name") String name){
		List<User> userList = userDAO.findByName(name);
		if(null != userList && userList.size() > 0){
			return ServiceUtil.buildEntity(HttpStatus.FOUND, userList);
		}else{
			return noRecordFound;
		}
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") String id){
		userDAO.delete(id);
		return noContent;
	}

	@Autowired
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Resource
	public void setNoRecordFound(ResponseEntity<Object> noRecordFound) {
		this.noRecordFound = noRecordFound;
	}
	
	@Resource
	public void setInvalidRequest(ResponseEntity<Object> invalidRequest) {
		this.invalidRequest = invalidRequest;
	}
	
	@Resource
	public void setNoContent(ResponseEntity<Object> noContent) {
		this.noContent = noContent;
	}
	
}
