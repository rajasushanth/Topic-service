package com.starkinc.stopic.repository;

import com.starkinc.stopic.dto.TopicsDTO;
import com.starkinc.stopic.entity.Message;
import com.starkinc.stopic.entity.Topic;

/**
 * @author RajaSushanth
 *
 */
public interface TopicCustomRepository {
	Topic findAndUpdateMessage(String topicName, Message message);
	TopicsDTO findByAuthorOrderByCreatedDesc(String author, long page);
	TopicsDTO findByAuthorAndTopicName(String author, String topicName, long page);
}
