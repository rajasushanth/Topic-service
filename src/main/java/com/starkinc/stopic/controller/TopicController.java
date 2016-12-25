package com.starkinc.stopic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starkinc.stopic.constants.Constants;
import com.starkinc.stopic.dao.TopicDAO;
import com.starkinc.stopic.entity.Error;
import com.starkinc.stopic.entity.Topic;
import com.starkinc.stopic.util.ServiceUtil;

@RestController
@RequestMapping(value="/topics", consumes = "application/json", produces = "application/json")
public class TopicController {
	
	private TopicDAO topicDAO;
	
	@PostMapping
	public ResponseEntity<Object> saveOrUpdate(@RequestBody Topic topic){
		return ServiceUtil.buildEntity(HttpStatus.CREATED, topicDAO.saveOrUpdate(topic));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") String id){
		return ServiceUtil.buildEntity(HttpStatus.FOUND, topicDAO.findOne(id));
	}
	
	@GetMapping("/search")
	public ResponseEntity<Object> findByTopicName(@RequestParam(name = "topic", required = false) String topicName,
			@RequestParam(name = "username", required = false) String userName){
		if(StringUtils.isEmpty(userName) && StringUtils.isEmpty(topicName)){
			return ServiceUtil.buildEntity(HttpStatus.BAD_REQUEST, new Error(HttpStatus.BAD_REQUEST, Constants.TOPIC_SEARCH));
		}
		return ServiceUtil.buildEntity(HttpStatus.FOUND, topicDAO.search(topicName, userName));
	}
	
	@GetMapping
	public ResponseEntity<Object> findAll(){
		return ServiceUtil.buildEntity(HttpStatus.FOUND, topicDAO.findAll());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String id){
		topicDAO.delete(id);
		return ServiceUtil.buildEntity(HttpStatus.NO_CONTENT, null);
	}
	
	@Autowired
	public TopicController(TopicDAO topicDAO) {
		this.topicDAO = topicDAO;
	}
	
	
}
