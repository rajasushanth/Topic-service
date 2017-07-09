package com.starkinc.stopic.repository;

import com.starkinc.stopic.entity.TopicUser;

/**
 * @author RajaSushanth
 *
 */
public interface UserCustomRepository {
	
	TopicUser findQuestionByUsername(String username);
	boolean verifyAccount(String username, String answer);
	boolean updateAccount(String username, String password);

}
