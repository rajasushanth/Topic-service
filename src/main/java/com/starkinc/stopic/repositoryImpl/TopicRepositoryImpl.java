package com.starkinc.stopic.repositoryImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.starkinc.stopic.entity.Topic;
import com.starkinc.stopic.repository.TopicCustomRepository;

public class TopicRepositoryImpl implements TopicCustomRepository {
	
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Topic> findByTopicNameOrUserName(String topicName, String userName) {
		Criteria criteria = new Criteria();
		Query query = new Query();
		if(StringUtils.isNotBlank(topicName)){
			query.addCriteria(criteria.orOperator(Criteria.where("topicName").regex(topicName)));
		}
		if(StringUtils.isNotBlank(userName)){
			query.addCriteria(Criteria.where("userName").regex(userName));
		}
		return mongoTemplate.find(query, Topic.class);
	}

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
