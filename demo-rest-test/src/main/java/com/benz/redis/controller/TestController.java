package com.benz.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.benz.redis.model.PrincipalUser;


@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/save")
	public void save()
	{

		PrincipalUser p_user=new PrincipalUser("benz@gmail.com","142652","67282");
		String url="http://localhost:9092/req/save";
		System.out.println(p_user.getToken());
		restTemplate.postForObject(url,p_user,PrincipalUser.class);
		System.out.println("saved in redis cache");
	}
}
