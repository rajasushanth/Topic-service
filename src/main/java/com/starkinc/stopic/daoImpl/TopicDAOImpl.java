package com.starkinc.stopic.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starkinc.stopic.dao.TopicDAO;
import com.starkinc.stopic.entity.Topic;
import com.starkinc.stopic.repository.TopicRepository;

@Component
public class TopicDAOImpl implements TopicDAO {

	private TopicRepository topicRepository;

	@Override
	public Topic saveOrUpdate(Topic topic) {
		return topicRepository.save(topic);
	}


	@Override
	public void delete(String id) {
		topicRepository.delete(id);
	}


	@Override
	public Topic findOne(String id) {
		return topicRepository.findOne(id);
	}


	@Override
	public List<Topic> search(String topicName, String userName) {
		return topicRepository.findByTopicNameOrUserName(topicName, userName);
	}


	@Override
	public List<Topic> findAll() {
		return topicRepository.findAll();
	}
	
	@Autowired
	public TopicDAOImpl(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}


}
