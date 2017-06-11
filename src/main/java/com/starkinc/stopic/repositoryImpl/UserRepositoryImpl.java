package com.starkinc.stopic.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.starkinc.stopic.entity.TopicUser;
import com.starkinc.stopic.repository.UserCustomRepository;
import com.starkinc.stopic.util.ServiceUtil;

public class UserRepositoryImpl implements UserCustomRepository {
	
	private MongoTemplate mongoTemplate;

	@Override
	public TopicUser findQuestionByUsername(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").regex(username));
		query.fields().include("question");
		TopicUser user = mongoTemplate.findOne(query, TopicUser.class);
		return user;
	}
	
	@Override
	public boolean verifyAccount(String username, String answer) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").regex(username));
		query.fields().include("answer");
		TopicUser user = mongoTemplate.findOne(query, TopicUser.class);
		return ServiceUtil.isValidAnswer(answer, user.getAnswer());
	}
	
	@Override
	public boolean updateAccount(String username, String password) {
		
		Update update = new Update();
		update.set("password", password);
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").regex(username));
		WriteResult result = mongoTemplate.updateFirst(query, update, TopicUser.class);
		return result.isUpdateOfExisting();
	}
	
	
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


}
