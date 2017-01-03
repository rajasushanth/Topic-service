package com.starkinc.stopic.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starkinc.stopic.dao.UserDAO;
import com.starkinc.stopic.entity.User;
import com.starkinc.stopic.repository.UserRepository;

@Component
public class UserDAOImpl implements UserDAO {
	
	private UserRepository userRepository;

	@Override
	public User saveOrUpdate(User user) {
		User u = userRepository.save(user);
		return u;
	}

	@Override
	public void delete(String id) {
		userRepository.delete(id);
	}

	@Override
	public User findOne(String id) {
		
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}

	@Autowired
	public UserDAOImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

}
