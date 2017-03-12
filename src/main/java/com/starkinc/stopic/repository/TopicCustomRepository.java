package com.starkinc.stopic.repository;

import java.util.List;

import com.starkinc.stopic.entity.Message;
import com.starkinc.stopic.entity.Topic;

public interface TopicCustomRepository {
	Topic findAndUpdateMessage(String topicName, Message message);
	List<String> findByAuthorOrderByCreatedDesc(String author);
}
