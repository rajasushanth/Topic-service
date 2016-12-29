package com.starkinc.stopic.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.starkinc.stopic.entity.Conversation;

public interface ConversationRepository extends MongoRepository<Conversation, Long>, ConversationCustomRepository {

}
