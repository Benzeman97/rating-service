package com.benz.redis.dao;

import java.util.Map;

import com.benz.redis.model.User;

public interface UserDAO {

	void save(User user);
	void delete(int userId);
	void update(User user);
	User findByUserId(int userId);
	Map<Integer,User> findAll();
}
