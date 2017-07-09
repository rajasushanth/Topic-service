package com.starkinc.stopic.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @author RajaSushanth
 *
 */
public class Topic {

	@Id
	private String topicName;
	@Indexed(background = true)
	private String author;
	private Date created;
	private Date updated;
	private List<Message> messages;

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Topic [topicName=");
		builder.append(topicName);
		builder.append(", author=");
		builder.append(author);
		builder.append(", created=");
		builder.append(created);
		builder.append(", updated=");
		builder.append(updated);
		builder.append(", messages=");
		builder.append(messages);
		builder.append("]");
		return builder.toString();
	}

}
