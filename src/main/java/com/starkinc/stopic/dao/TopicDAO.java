package com.starkinc.stopic.dao;

import java.util.List;

import com.starkinc.stopic.entity.Message;
import com.starkinc.stopic.entity.Topic;

public interface TopicDAO {

	Topic save(Topic topic);

	void delete(String topicName);

	Topic findOne(String topicName);

	List<String> findByAuthor(String author);

	List<String> findAll();
	
	List<Message> updateMessage(String topicName, Message message);
	
	boolean isTopicExist(String topicName);
}
