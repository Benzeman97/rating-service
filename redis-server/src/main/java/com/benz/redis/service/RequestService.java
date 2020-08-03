package com.benz.redis.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.benz.redis.dao.RequestDAO;
import com.benz.redis.model.PrincipalUser;

@Service
public class RequestService implements RequestDAO{
	
	private HashOperations<String,String,PrincipalUser> hashOperations;
	
	@Autowired
    public RequestService(@Qualifier("request") RedisTemplate<String,PrincipalUser> redisTemplate) {
		this.hashOperations=redisTemplate.opsForHash();
	}

	@Override
	public void save(PrincipalUser req) {
		
		hashOperations.put("SignIn",req.getUserName(),req);
	}

	@Override
	public void delete(String userName) {
		hashOperations.delete("SignIn", userName);
		
	}

	@Override
	public PrincipalUser getRequest(String userName) {
		return hashOperations.get("SignIn",userName);
	}

	@Override
	public Map<String, PrincipalUser> gerRequests() {
		return hashOperations.entries("SignIn");
	}

	@Override
	public void update(PrincipalUser req) {
		hashOperations.put("SignIn",req.getUserName(),req);
		
	}

}
