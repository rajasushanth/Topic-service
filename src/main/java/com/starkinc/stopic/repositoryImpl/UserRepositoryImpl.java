package com.starkinc.stopic.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.starkinc.stopic.entity.TopicUser;
import com.starkinc.stopic.repository.UserCustomRepository;

public class UserRepositoryImpl implements UserCustomRepository {
	
	private MongoTemplate mongoTemplate;

	@Override
	public String findQuestionByUsername(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").regex(username));
		query.fields().include("question");
		query.fields().include("answer");
		TopicUser user = mongoTemplate.findOne(query, TopicUser.class);
		return user.getQuestion();
	}
	
	
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
