package com.benz.catalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.benz.catalog.model.Movie;
import com.benz.catalog.model.MovieCatalog;
import com.benz.catalog.model.UserRating;

@Service
public class MovieCatalogService {
	
//	@Autowired
//	RestTemplate restTemplate;
	
	@Autowired
	WebClient.Builder webClient_builder;

	public List<MovieCatalog> getMovieCatalog(@PathVariable("userId") int userId)
	{
//		return Arrays.asList(
//				new MovieCatalog("Avengers","average",5.5),
//				new MovieCatalog("Harry Potter","Good",7.3),
//				new MovieCatalog("Braking Bad","Excellent",9.4)
//				);
		
//		UserRating userRating = restTemplate.getForObject("http://localhost:8083/rating/user/"+userId,UserRating.class);
		
		UserRating userRating= webClient_builder.build().get().uri("http://localhost:8083/rating/user/"+userId)
		    .retrieve().bodyToMono(UserRating.class).block();
		  
		  
		return userRating.getRatings().stream().map(rating ->{
			
//			Movie movie=restTemplate.getForObject("http://localhost:8082/movieInfo/"+rating.getMovieId(),Movie.class);
	
			 Movie movie=webClient_builder.build().get().uri("http://localhost:8082/movieInfo/"+rating.getMovieId())
			       .retrieve().bodyToMono(Movie.class).block();
			 
			 
			return new MovieCatalog(movie.getMovieName(),"not bad",rating.getRating());
			
		}).collect(Collectors.toList());
		
		
	}
}
