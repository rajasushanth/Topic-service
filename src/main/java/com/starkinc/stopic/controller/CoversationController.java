package com.starkinc.stopic.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
import com.starkinc.stopic.entity.Error;
import com.starkinc.stopic.util.ServiceUtil;

/**
 * Created by Dell on 12/30/2016.
 */
@RestController
@RequestMapping(value = "/conversations", consumes = "application/json", produces = "application/json")
public class CoversationController {
	
	private ConversationDAO conversationDAO;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByRefId(@PathVariable("id") String id){
    	Conversation conversation = conversationDAO.findByRefId(id);
    	if(null != conversation){
    		return ServiceUtil.buildEntity(HttpStatus.FOUND, conversation);
    	}else{
    		return ServiceUtil.buildEntity(HttpStatus.NOT_FOUND, new Error(HttpStatus.NOT_FOUND, Constants.NO_RECORD_FOUND));
    	}
    }
    
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Map<String, String> body){
    	if(null != body && null != body.get(Constants.TOPIC_REF)){
    		return ServiceUtil.buildEntity(HttpStatus.CREATED ,conversationDAO.save(body.get(Constants.TOPIC_REF)));
    	}else{
    		return ServiceUtil.buildEntity(HttpStatus.BAD_REQUEST, new Error(HttpStatus.BAD_REQUEST, Constants.INVALID_REQUEST));
    	}
    }
    
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Conversation conversation){
    	if(null != conversation && !StringUtils.isEmpty(conversation.getTopicRef()) && null != conversation.getMessageList()
    				&& null != conversation.getMessageList().get(0)){
    		Conversation conversationFetched = conversationDAO.update(conversation.getTopicRef(), conversation.getMessageList().get(0));
        	if(null != conversationFetched && null != conversationFetched.getMessageList()){
        		return ServiceUtil.buildEntity(HttpStatus.OK, conversationFetched.getMessageList());
        	}else{
        		return ServiceUtil.buildEntity(HttpStatus.NOT_FOUND, new Error(HttpStatus.NOT_FOUND, Constants.NO_RECORD_FOUND));
        	}
    	}else{
    		return ServiceUtil.buildEntity(HttpStatus.BAD_REQUEST, new Error(HttpStatus.BAD_REQUEST, Constants.INVALID_REQUEST));
    	}
    	
    }
    
    @Autowired
	public CoversationController(ConversationDAO conversationDAO) {
		this.conversationDAO = conversationDAO;
	}
    
    
    
}
