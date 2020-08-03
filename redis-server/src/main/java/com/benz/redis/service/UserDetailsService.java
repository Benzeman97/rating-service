package com.benz.redis.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.benz.redis.dao.UserDAO;
import com.benz.redis.model.User;

@Service
public class UserDetailsService implements UserDAO{
	

	private HashOperations<String,Integer,User> hashOperations;
	
	 @Autowired
     public UserDetailsService(RedisTemplate<String,User> redisTemplate) {
   
		this.hashOperations=redisTemplate.opsForHash();
	}

	@Override
	public void save(User user) {
		hashOperations.put("USER",user.getUserId(),user);
		
	}

	@Override
	public void delete(int userId) {
		hashOperations.delete("USER",userId);
		
	}

	@Override
	public void update(User user) {
		hashOperations.put("USER",user.getUserId(),user);
		
	}

	@Override
	public User findByUserId(int userId) {
		return hashOperations.get("USER",userId);
	}

	@Override
	public Map<Integer,User> findAll() {
		 return hashOperations.entries("USER");
	}

	
}
