package com.starkinc.stopic.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Topic {
	
	@Id
	private String id;
	private String topicName;
	private String userName;
	private Date created = new Date();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Topic [id=");
		builder.append(id);
		builder.append(", topicName=");
		builder.append(topicName);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", created=");
		builder.append(created);
		builder.append("]");
		return builder.toString();
	}
	
	
}
