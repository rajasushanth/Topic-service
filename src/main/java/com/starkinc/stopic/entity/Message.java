package com.starkinc.stopic.entity;

import java.util.Date;

public class Message {
	
	private String name;
	private String message;
	private Date posted;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getPosted() {
		return posted;
	}
	public void setPosted(Date posted) {
		this.posted = posted;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [name=");
		builder.append(name);
		builder.append(", message=");
		builder.append(message);
		builder.append(", posted=");
		builder.append(posted);
		builder.append("]");
		return builder.toString();
	}
	
	
}
