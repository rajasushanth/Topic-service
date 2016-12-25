package com.starkinc.stopic.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starkinc.stopic.dao.UserDAO;
import com.starkinc.stopic.entity.User;
import com.starkinc.stopic.util.ServiceUtil;

@RestController
@RequestMapping(value = "/users",consumes = "application/json", produces = "application/json")
public class UserController {
	
	private UserDAO userDAO;
	
	@PostMapping
	public ResponseEntity<Object> saveOrUpdateUser(@RequestBody User user){
		return ServiceUtil.buildEntity(HttpStatus.CREATED, userDAO.saveOrUpdate(user)) ;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Object> findUserById(@PathVariable("id") String id){
		return ServiceUtil.buildEntity(HttpStatus.FOUND, userDAO.findOne(id));
	}
	
	@GetMapping(value="/findByName")
	public ResponseEntity<Object> findUserByName(@PathParam("name") String name){
		return ServiceUtil.buildEntity(HttpStatus.FOUND, userDAO.findByName(name));
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") String id){
		userDAO.delete(id);
		return ServiceUtil.buildEntity(HttpStatus.NO_CONTENT, null);
	}
	
	@Autowired
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}