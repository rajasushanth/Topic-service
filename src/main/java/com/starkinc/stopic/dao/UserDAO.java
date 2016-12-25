package com.starkinc.stopic.dao;

import java.util.List;

import com.starkinc.stopic.entity.User;

public interface UserDAO {
	
	User saveOrUpdate(User user);
	void delete(String id);
	User findOne(String id);
	List<User> findByName(String name);

}
