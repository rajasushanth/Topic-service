package com.starkinc.stopic.daoImpl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starkinc.stopic.dao.ConversationDAO;
import com.starkinc.stopic.entity.Conversation;
import com.starkinc.stopic.entity.Message;
import com.starkinc.stopic.repository.ConversationRepository;

@Component
public class ConversationDAOImpl implements ConversationDAO {
	
	private ConversationRepository conversationRepository;

	@Override
	public Conversation findByRefId(String id) {
		return conversationRepository.findByTopicRef(new ObjectId(id));
	}

	@Override
	public Conversation save(String topicRef) {
		Conversation conversation = new Conversation();
		conversation.setTopicRef(new ObjectId(topicRef));
		return conversationRepository.save(conversation);
	}

	@Override
	public Conversation update(String topicRef, Message message) {
		return conversationRepository.findAndModiy(message, topicRef);
	}
	
	@Autowired
	public ConversationDAOImpl(ConversationRepository conversationRepository) {
		this.conversationRepository = conversationRepository;
	}
	
	

}
