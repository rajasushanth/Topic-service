package com.starkinc.stopic.dao;

import com.starkinc.stopic.entity.Conversation;
import com.starkinc.stopic.entity.Message;

public interface ConversationDAO {
	
	Conversation findByRefId(String id);
	Conversation save(String topicRef);
	Conversation update(String topicRef,Message message);
}
