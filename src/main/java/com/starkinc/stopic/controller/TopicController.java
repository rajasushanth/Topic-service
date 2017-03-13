package com.starkinc.stopic.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starkinc.stopic.dao.TopicDAO;
import com.starkinc.stopic.entity.Message;
import com.starkinc.stopic.entity.Topic;
import com.starkinc.stopic.util.ServiceUtil;

@RestController
@RequestMapping(value = "/topics", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicController {

	private TopicDAO topicDAO;
	private ResponseEntity<Object> noRecordFound;
	private ResponseEntity<Object> invalidRequest;
	private ResponseEntity<Object> noContent;
	private ResponseEntity<Object> topicSearchValidation;
	private ResponseEntity<Object> topicAlreadyExist;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody Topic topic) {
		if (null != topic && StringUtils.isNotBlank(topic.getTopicName())
				&& StringUtils.isNotBlank(topic.getAuthor())) {
			if (!topicDAO.isTopicExist(topic.getTopicName())) {
				topic.setCreated(new Date());
				topic.setUpdated(new Date());
				return ServiceUtil.buildEntity(HttpStatus.CREATED, topicDAO.save(topic));
			} else {
				return topicAlreadyExist;
			}
		} else {
			return invalidRequest;
		}

	}

	@RequestMapping("/{topicName}")
	public ResponseEntity<Object> findByTopicName(@PathVariable("topicName") String topicName) {
		Topic topic = topicDAO.findOne(topicName);
		if (null != topic) {
			return ServiceUtil.buildEntity(HttpStatus.FOUND, topic);
		} else {
			return noRecordFound;
		}
	}

	@RequestMapping("/search")
	public ResponseEntity<Object> findByAuthor(@RequestParam(name = "author", required = false) String author) {
		if (StringUtils.isNotBlank(author)) {
			List<String> topicList = topicDAO.findByAuthor(author);
			if (null != topicList && topicList.size() > 0) {
				return ServiceUtil.buildEntity(HttpStatus.FOUND, topicList);
			} else {
				return noRecordFound;
			}
		} else {
			return topicSearchValidation;
		}
	}

	@RequestMapping
	public ResponseEntity<Object> findAll() {
		List<String> topicList = topicDAO.findAll();
		if (null != topicList && topicList.size() > 0) {
			return ServiceUtil.buildEntity(HttpStatus.FOUND, topicList);
		} else {
			return noRecordFound;
		}
	}

	@RequestMapping(value = "/{topicName}", method = RequestMethod.PUT)
	public ResponseEntity<Object> findAndUpdateMessage(@PathVariable("topicName") String topicName,
			@RequestBody Message message) {
		if (StringUtils.isNotBlank(topicName) && null != message) {
			message.setPosted(new Date());
			return ServiceUtil.buildEntity(HttpStatus.OK, topicDAO.updateMessage(topicName, message));
		}

		return invalidRequest;

	}

	@RequestMapping(value = "/{topicName}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("topicName") String topicName) {
		topicDAO.delete(topicName);
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

	@Resource
	public void setTopicAlreadyExist(ResponseEntity<Object> topicAlreadyExist) {
		this.topicAlreadyExist = topicAlreadyExist;
	}

}
