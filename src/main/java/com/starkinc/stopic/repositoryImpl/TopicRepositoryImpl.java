package com.starkinc.stopic.repositoryImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.starkinc.stopic.constants.Constants;
import com.starkinc.stopic.dto.TopicsDTO;
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
		update.currentDate("updated");
		update.addToSet("messages", message);
		Topic topic = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true),
				Topic.class);
		return topic;
	}

	@Override
	public TopicsDTO findByAuthorOrderByCreatedDesc(String author, long page) {
		Query query = new Query();
		Query countQuery = new Query();
		if(StringUtils.isNoneBlank(author)){
			query.addCriteria(Criteria.where("author").regex(author));
			countQuery.addCriteria(Criteria.where("author").regex(author));
		}
		long total = mongoTemplate.count(countQuery, Topic.class);
		int initPageToSkip = (int)(page-1) * Constants.LIMIT;
		int finalPageToSkip = (initPageToSkip > total)? 0 : initPageToSkip;
		page = (finalPageToSkip == 0) ? 1 : page;
		query.fields().include("topicName");
		query.with(new Sort(new Order(Direction.DESC, "created")));
		query.skip(finalPageToSkip);
		query.limit(Constants.LIMIT);
		List<Topic> topics = mongoTemplate.find(query, Topic.class);
		TopicsDTO topicsDTO = new TopicsDTO(topics, total, page);
		return topicsDTO;
	}

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}