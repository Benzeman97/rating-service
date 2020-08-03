package com.benz.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.benz.redis.model.PrincipalUser;
import com.benz.redis.model.User;

@Configuration
public class RedisConfig {
	

	@Bean
	public JedisConnectionFactory getRedisConnectionFactory()
	{
		return new JedisConnectionFactory(
				new RedisStandaloneConfiguration("localhost",6379)
				);
	}
	
	
	@Bean(name="redisTemplate")
	public RedisTemplate<String,User> redisTemplateUser()
	{
		RedisTemplate<String,User> redisTemplate= new RedisTemplate<>();
		redisTemplate.setConnectionFactory(getRedisConnectionFactory());
		
		return redisTemplate;
	}
	
	@Bean(name="request")
	public RedisTemplate<String,PrincipalUser> redisTemplateRequest()
	{
		RedisTemplate<String,PrincipalUser> redisTemplate= new RedisTemplate<>();
		redisTemplate.setConnectionFactory(getRedisConnectionFactory());
		
		return redisTemplate;
	}
	


}
