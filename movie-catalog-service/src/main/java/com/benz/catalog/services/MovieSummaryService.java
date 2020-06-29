package com.benz.catalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.benz.catalog.model.MovieCatalog;
import com.benz.catalog.model.MovieRating;
import com.benz.catalog.model.MovieSummary;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieSummaryService {
	
	@Autowired
	WebClient.Builder webClient_builder;
	
	@Value("${movie.info.service.url}")
	private String info_service;

	@HystrixCommand(fallbackMethod = "getfallBackMovieCatalog",
			threadPoolKey="movieSummaryPool", threadPoolProperties= {
					@HystrixProperty(name = "coreSize",value = "50"),
					@HystrixProperty(name = "maxQueueSize",value = "25")
			})
	public MovieCatalog getMovieSummary(MovieRating rating) 
	{
	
		 MovieSummary movieSummary=webClient_builder.build().get().uri(info_service+rating.getMovieId())
			       .retrieve().bodyToMono(MovieSummary.class).block();
		 
		return new MovieCatalog(movieSummary.getOriginal_title(),movieSummary.getOverview(),rating.getRating());
		
	}
	
	public MovieCatalog getfallBackMovieCatalog(MovieRating rating) throws Exception
	{
		return new MovieCatalog("Francisca Uduranga","Very bad",rating.getRating());
	}
}
