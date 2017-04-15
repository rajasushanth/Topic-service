package com.starkinc.stopic.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.starkinc.stopic.entity.TopicUser;

//@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<TopicUser, String> {
	
	List<TopicUser> findByUsername(@Param("name") String name);

}
