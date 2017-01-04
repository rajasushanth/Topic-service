package com.starkinc.stopic.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.starkinc.stopic.entity.Conversation;

public interface ConversationRepository extends MongoRepository<Conversation, String>, ConversationCustomRepository {
	Conversation findByTopicRef(ObjectId topicRef);
}
