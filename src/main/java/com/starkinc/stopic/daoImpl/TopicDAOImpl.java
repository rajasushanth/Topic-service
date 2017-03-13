package com.starkinc.stopic.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starkinc.stopic.dao.TopicDAO;
import com.starkinc.stopic.entity.Message;
import com.starkinc.stopic.entity.Topic;
import com.starkinc.stopic.repository.TopicRepository;

@Component
public class TopicDAOImpl implements TopicDAO {

	private TopicRepository topicRepository;

	@Override
	public Topic save(Topic topic) {
		return topicRepository.save(topic);
	}

	@Override
	public void delete(String topicName) {
		topicRepository.delete(topicName);
	}

	@Override
	public Topic findOne(String topicName) {
		return topicRepository.findOne(topicName);
	}

	@Override
	public List<String> findByAuthor(String author) {
		return topicRepository.findAllOrByAuthorOrderByCreatedDesc(author);
	}

	@Override
	public List<String> findAll() {
		return topicRepository.findAllOrByAuthorOrderByCreatedDesc(null);
	}

	@Override
	public List<Message> updateMessage(String topicName, Message message) {
		return topicRepository.findAndUpdateMessage(topicName, message);
	}

	@Override
	public boolean isTopicExist(String topicName) {
		return topicRepository.exists(topicName);
	}
	
	@Autowired
	public TopicDAOImpl(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}

}
