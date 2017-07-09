package com.starkinc.stopic.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.starkinc.stopic.entity.TopicUser;

//@RepositoryRestResource(collectionResourceRel = "users", path = "users")
/**
 * @author RajaSushanth
 *
 */
public interface UserRepository extends MongoRepository<TopicUser, String>, UserCustomRepository {
	
}
