package com.starkinc.stopic.dao;

import java.util.List;

import com.starkinc.stopic.entity.Message;
import com.starkinc.stopic.entity.Topic;

public interface TopicDAO {

	Topic save(Topic topic);

	void delete(String id);

	Topic findOne(String topicName);

	List<String> findByAuthor(String author);

	List<Topic> findAll();
	
	Topic updateMessage(String topicName, Message message);
	
	boolean isTopicExist(String topicName);
}
