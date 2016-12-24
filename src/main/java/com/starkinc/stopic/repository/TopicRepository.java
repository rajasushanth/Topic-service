package com.starkinc.stopic.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.starkinc.stopic.entity.Topic;

@RepositoryRestResource(collectionResourceRel="topics", path="topics")
public interface TopicRepository extends MongoRepository<Topic, String>{

}
