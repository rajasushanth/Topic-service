package com.starkinc.stopic.repository;

import java.util.List;

import com.starkinc.stopic.entity.Topic;

public interface TopicCustomRepository {
	List<Topic> findByTopicNameOrUserName(String topicName, String userName);
}
