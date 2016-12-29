package com.starkinc.stopic.repository;

import com.starkinc.stopic.entity.Conversation;
import com.starkinc.stopic.entity.Message;

public interface ConversationCustomRepository {
	Conversation findAndModiy(Message message, String topicRef);
}
