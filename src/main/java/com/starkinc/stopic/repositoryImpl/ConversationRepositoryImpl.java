package com.starkinc.stopic.repositoryImpl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import com.starkinc.stopic.entity.Conversation;
import com.starkinc.stopic.entity.Message;
import com.starkinc.stopic.repository.ConversationCustomRepository;

public class ConversationRepositoryImpl implements ConversationCustomRepository {
	
	private MongoTemplate mongoTemplate;

	@Override
	public Conversation findAndModiy(Message message, String topicRef) {
		Conversation conversationFetched = null;
		if(null != message){
			if(!StringUtils.isEmpty(message.getName()) && !StringUtils.isEmpty(message.getMessage()) 
					&& !StringUtils.isEmpty(message.getPosted())){
				Query query = new Query();
				ObjectId objectId = new ObjectId(topicRef);
				query.addCriteria(Criteria.where("topicRef").is(objectId));
				query.fields().include("messages").exclude("_id");
				Update update = new Update();
				update.addToSet("messages", message);
				conversationFetched = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), Conversation.class);
			}
		}
		return conversationFetched;
	}

	@Autowired
	public ConversationRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
