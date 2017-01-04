package com.starkinc.stopic.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Conversation {
	
	@Id
	private String id;
	private ObjectId topicRef;
	private List<Message> messages;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTopicRef() {
		return topicRef.toHexString();
	}
	public void setTopicRef(ObjectId topicRef) {
		this.topicRef = topicRef;
	}
	public List<Message> getMessageList() {
		return messages;
	}
	public void setMessageList(List<Message> messageList) {
		this.messages = messageList;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conversation [id=");
		builder.append(id);
		builder.append(", topicRef=");
		builder.append(topicRef);
		builder.append(", messageList=");
		builder.append(messages);
		builder.append("]");
		return builder.toString();
	}
	
	
}
