package com.benz.redis.dao;

import java.util.Map;

import com.benz.redis.model.PrincipalUser;

public interface RequestDAO {

	void save(PrincipalUser req);
	void delete(String userName);
	PrincipalUser getRequest(String userName);
	Map<String,PrincipalUser> gerRequests();
	void update(PrincipalUser req);
}
