package com.starkinc.stopic.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.starkinc.stopic.entity.TopicUser;
import com.starkinc.stopic.repository.UserRepository;

/**
 * @author RajaSushanth
 *
 */
@Service
public class TopicUserService implements UserDetailsService {
	
	
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TopicUser topicUser = userRepository.findOne(username);
		if(null != topicUser){
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(topicUser.getRole()));
			return new User(topicUser.getUsername(), topicUser.getPassword(), topicUser.isEnabled(), true, true, true, authorities);
		}
		throw new UsernameNotFoundException("User "+ username + " not found");
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	

}
