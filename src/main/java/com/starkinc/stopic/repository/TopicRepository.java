package com.starkinc.stopic.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.starkinc.stopic.entity.Topic;

//@RepositoryRestResource(collectionResourceRel="topics", path="topics") -- Used to expose a repository as rest service
public interface TopicRepository extends MongoRepository<Topic, String>{

}
