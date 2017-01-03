package com.starkinc.stopic.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.starkinc.stopic.entity.Topic;
import com.starkinc.stopic.util.ServiceUtil;

@RestController
@RequestMapping(value="/topics", consumes = Constants.APPLICATION_JSON, produces = Constants.APPLICATION_JSON)
public class TopicController {
	
	private TopicDAO topicDAO;
	private ResponseEntity<Object> noRecordFound;
	private ResponseEntity<Object> invalidRequest;
	private ResponseEntity<Object> noContent;
	private ResponseEntity<Object> topicSearchValidation;
	
	@PostMapping
	public ResponseEntity<Object> saveOrUpdate(@RequestBody Topic topic){
		if(null != topic && StringUtils.isNotBlank(topic.getTopicName())
				&&  StringUtils.isNotBlank(topic.getUserName())){
			return ServiceUtil.buildEntity(HttpStatus.CREATED, topicDAO.saveOrUpdate(topic));
		}else{
			return invalidRequest;
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") String id){
		Topic topic = topicDAO.findOne(id);
		if(null != topic){
			return ServiceUtil.buildEntity(HttpStatus.FOUND, topic);
		}else{
			return noRecordFound;
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<Object> findByTopicName(@RequestParam(name = "topic", required = false) String topicName,
			@RequestParam(name = "username", required = false) String userName){
		if(StringUtils.isNotBlank(userName) || StringUtils.isNotBlank(topicName)){
			List<Topic> topicList = topicDAO.search(topicName, userName);
			if(null != topicList && topicList.size() > 0){
				return ServiceUtil.buildEntity(HttpStatus.FOUND, topicList);
			}else{
				return noRecordFound;
			}
		}else{
			return topicSearchValidation;
		}
	}
	
	@GetMapping
	public ResponseEntity<Object> findAll(){
		List<Topic> topicList  =  topicDAO.findAll();
		if(null != topicList && topicList.size() > 0){
			return ServiceUtil.buildEntity(HttpStatus.FOUND, topicList);
		}else{
			return noRecordFound;
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String id){
		topicDAO.delete(id);
		return noContent;
	}
	
	@Autowired
	public TopicController(TopicDAO topicDAO) {
		this.topicDAO = topicDAO;
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

	@Resource
	public void setTopicSearchValidation(ResponseEntity<Object> topicSearchValidation) {
		this.topicSearchValidation = topicSearchValidation;
	}

}
