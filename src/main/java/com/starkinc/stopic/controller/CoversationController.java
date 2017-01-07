package com.starkinc.stopic.controller;

import java.util.Map;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starkinc.stopic.constants.Constants;
import com.starkinc.stopic.dao.ConversationDAO;
import com.starkinc.stopic.entity.Conversation;
import com.starkinc.stopic.util.ServiceUtil;

/**
 * Created by Dell on 12/30/2016.
 */
@RestController
@RequestMapping(value = "/conversations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CoversationController {
	
	private ConversationDAO conversationDAO;
	private ResponseEntity<Object> noRecordFound;
	private ResponseEntity<Object> invalidRequest;
	private ResponseEntity<Object> noContent;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByRefId(@PathVariable("id") String id){
    	Conversation conversation = conversationDAO.findByRefId(id);
    	if(null != conversation){
    		return ServiceUtil.buildEntity(HttpStatus.FOUND, conversation);
    	}else{
    		return noRecordFound;
    	}
    }
    
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Map<String, String> body){
    	if(null != body && null != body.get(Constants.TOPIC_REF)){
    		return ServiceUtil.buildEntity(HttpStatus.CREATED ,conversationDAO.save(body.get(Constants.TOPIC_REF)));
    	}else{
    		return invalidRequest;
    	}
    }
    
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Conversation conversation){
    	if(null != conversation && StringUtils.isNotBlank(conversation.getTopicRef()) && null != conversation.getMessageList()
    				&& null != conversation.getMessageList().get(0)){
    		Conversation conversationFetched = conversationDAO.update(conversation.getTopicRef(), conversation.getMessageList().get(0));
        	if(null != conversationFetched && null != conversationFetched.getMessageList()){
        		return ServiceUtil.buildEntity(HttpStatus.OK, conversationFetched.getMessageList());
        	}else{
        		return noRecordFound;
        	}
    	}else{
    		return invalidRequest;
    	}
    	
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
    	conversationDAO.delete(id);
    	return noContent;
    }
    
    @Autowired
	public CoversationController(ConversationDAO conversationDAO) {
		this.conversationDAO = conversationDAO;
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
