package com.starkinc.stopic.repository;

import java.util.List;

import com.starkinc.stopic.entity.Message;

public interface TopicCustomRepository {
	List<Message> findAndUpdateMessage(String topicName, Message message);
	List<String> findByAuthorOrderByCreatedDesc(String author);
}
