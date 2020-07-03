package com.benz.catalog.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.benz.catalog.model.MovieRating;
import com.benz.catalog.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserRatingService {
	
	@Autowired
	WebClient.Builder webClient_builder;
	
  
	@Value("${rating.data.service.url}")
	private String rating_service;

	@HystrixCommand(fallbackMethod = "getfallBackUserRating",
			threadPoolKey="userRatingPool", threadPoolProperties= {
					@HystrixProperty(name = "coreSize",value = "50"),
					@HystrixProperty(name = "maxQueueSize",value = "25")
			})
	public UserRating getUserRating(int userId)
	{
		
		
		UserRating userRating= webClient_builder.build().get().uri(rating_service+userId)
				 .retrieve().bodyToMono(UserRating.class).block();
		
		return userRating;
			
	}
	public UserRating getfallBackUserRating(int userId)
	{
		
		UserRating userRating=new UserRating();
		userRating.setMovieId(101);
		userRating.setRatings(Arrays.asList(
				new MovieRating(51,4.3)));
		System.out.println("Error occured");
		
		return userRating;
	}
}
