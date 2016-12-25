package com.starkinc.stopic.dao;

import java.util.List;

import com.starkinc.stopic.entity.Topic;

public interface TopicDAO {
	
	Topic saveOrUpdate(Topic topic);
	void delete(String id);
	Topic findOne(String id);
	List<Topic> search(String topicName, String userName);
	List<Topic> findAll();
}
