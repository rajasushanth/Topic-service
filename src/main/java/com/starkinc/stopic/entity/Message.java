package com.starkinc.stopic.entity;

import java.util.Date;

public class Message {

	private String commentator;
	private String message;
	private Date posted;

	public String getCommentator() {
		return commentator;
	}

	public void setCommentator(String commentator) {
		this.commentator = commentator;
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
		builder.append("Message [commentator=");
		builder.append(commentator);
		builder.append(", message=");
		builder.append(message);
		builder.append(", posted=");
		builder.append(posted);
		builder.append("]");
		return builder.toString();
	}
	

}