package com.starkinc.stopic.repositoryImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.starkinc.stopic.entity.Message;
import com.starkinc.stopic.entity.Topic;
import com.starkinc.stopic.repository.TopicCustomRepository;

public class TopicRepositoryImpl implements TopicCustomRepository {

	private MongoTemplate mongoTemplate;

	@Override
	public Topic findAndUpdateMessage(String topicName, Message message) {
		Query query = new Query();
		if (StringUtils.isNotBlank(topicName)) {
			query.addCriteria(Criteria.where("topicName").regex(topicName));
		}
		Update update = new Update();
		update.addToSet("messages", message);
		return mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), Topic.class);
	}

	@Override
	public List<String> findByAuthorOrderByCreatedDesc(String author) {
		Query query = new Query();
		query.addCriteria(Criteria.where("author").regex(author));
		query.fields().include("topicName");
		return mongoTemplate.find(query, String.class);
	}
	
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}