package com.benz.info.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.benz.info.model.MovieSummary;

@Service
public class MovieInfoService {
	
	@Autowired
	WebClient.Builder webClient;
	
	@Value("${movie.api_key}")
	private String api_key;
	
	@Value("${movie.url}")
	private String movie_url;

	public MovieSummary getMovieSummary(int movieId)
	{
		
		MovieSummary summary = webClient.build().get().uri(movie_url+movieId+"?api_key="+api_key)
		    .retrieve().bodyToMono(MovieSummary.class).block();
		  
		return summary;
	}
}
