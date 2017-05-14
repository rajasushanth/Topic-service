package com.starkinc.stopic.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starkinc.stopic.entity.Message;
import com.starkinc.stopic.entity.Topic;
import com.starkinc.stopic.repository.TopicRepository;
import com.starkinc.stopic.util.ServiceUtil;

@RestController
@RequestMapping(value = "/topics", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class TopicController {

	private TopicRepository topicRepository;
	private ResponseEntity<Object> noRecordFound;
	private ResponseEntity<Object> invalidRequest;
	private ResponseEntity<Object> noContent;
	private ResponseEntity<Object> topicSearchValidation;
	private ResponseEntity<Object> topicAlreadyExist;

	@RequestMapping(method = POST)
	public ResponseEntity<Object> save(@RequestBody Topic topic) {
		if (null != topic && StringUtils.isNotBlank(topic.getTopicName())
				&& StringUtils.isNotBlank(topic.getAuthor())) {
			if (!topicRepository.exists(topic.getTopicName())) {
				Date date = Calendar.getInstance().getTime();
				topic.setCreated(date);
				topic.setUpdated(date);
				return ServiceUtil.buildEntity(CREATED, topicRepository.save(topic));
			} else {
				return topicAlreadyExist;
			}
		} else {
			return invalidRequest;
		}

	}

	@RequestMapping("/{topicName}")
	public ResponseEntity<Object> findByTopicName(@PathVariable("topicName") String topicName) {
		Topic topic = topicRepository.findOne(topicName);
		if (null != topic) {
			return ServiceUtil.buildEntity(FOUND, topic);
		} else {
			return noRecordFound;
		}
	}

	@RequestMapping("/search")
	public ResponseEntity<Object> findByAuthor(@RequestParam(name = "author", required = false) String author) {
		if (StringUtils.isNotBlank(author)) {
			List<String> topicList = topicRepository.findAllOrByAuthorOrderByCreatedDesc(author);
			if (null != topicList && topicList.size() > 0) {
				return ServiceUtil.buildEntity(FOUND, topicList);
			} else {
				return noRecordFound;
			}
		} else {
			return topicSearchValidation;
		}
	}

	@RequestMapping
	public ResponseEntity<Object> findAll() {
		List<String> topicList = topicRepository.findAllOrByAuthorOrderByCreatedDesc(null);
		if (null != topicList && topicList.size() > 0) {
			return ServiceUtil.buildEntity(FOUND, topicList);
		} else {
			return noRecordFound;
		}
	}

	@RequestMapping(value = "/{topicName}", method = PUT)
	public ResponseEntity<Object> findAndUpdateMessage(@PathVariable("topicName") String topicName,
			@RequestBody Message message) {
		if (StringUtils.isNotBlank(topicName) && null != message) {
			message.setPosted(new Date());
			return ServiceUtil.buildEntity(OK, topicRepository.findAndUpdateMessage(topicName, message));
		}

		return invalidRequest;

	}

	@RequestMapping(value = "/{topicName}", method = DELETE)
	public ResponseEntity<Object> delete(@PathVariable("topicName") String topicName) {
		topicRepository.delete(topicName);
		return noContent;
	}
	
	@Autowired
	public void setTopicRepository(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
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
