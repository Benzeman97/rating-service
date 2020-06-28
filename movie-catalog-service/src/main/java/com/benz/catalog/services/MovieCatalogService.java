package com.benz.catalog.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.benz.catalog.model.MovieCatalog;
import com.benz.catalog.model.MovieRating;
import com.benz.catalog.model.MovieSummary;
import com.benz.catalog.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieCatalogService {
	
//	@Autowired
//	RestTemplate restTemplate;
	
//	@Autowired
//	DiscoveryClient discoverClient;
//	
//	@Autowired
//	WebClient.Builder webClient_builder;
//	
//	@Value("${rating.data.service.url}")
//	private String rating_service;
//	
//	@Value("${movie.info.service.url}")
//	private String info_service;
	
	@Autowired
	UserRatingService userRating_service;
	
	@Autowired
	MovieSummaryService movieSummary_service;
	
	@Value("${movie.overview1}")
	String overView1;
	
	@Value("${movie.overview2}")
	String overView2;
	
	public List<MovieCatalog> getMovieCatalog(int userId) 
	{
		System.out.println("Okay");
		
		UserRating userRating=userRating_service.getUserRating(userId);
		
		return userRating.getRatings().stream().map(rating ->{
			return movieSummary_service.getMovieSummary(rating);
		}).collect(Collectors.toList());
	}
	
	
	
//	@HystrixCommand(fallbackMethod = "getfallBackMovieCatalog")
//	public MovieCatalog getMovieCatalog(MovieRating rating)
//	{
//		return Arrays.asList(
//				new MovieCatalog("Avengers","average",5.5),
//				new MovieCatalog("Harry Potter","Good",7.3),
//				new MovieCatalog("Braking Bad","Excellent",9.4)
//				);
		
//		UserRating userRating = restTemplate.getForObject("http://rating-data-service/rating/user/"+userId,UserRating.class);
		
//		UserRating userRating= webClient_builder.build().get().uri(rating_service+userId)
//		    .retrieve().bodyToMono(UserRating.class).block();
		  
		  
//		return userRating.getRatings().stream().map(rating ->{
			
//			Movie movie=restTemplate.getForObject("http://movie-info-service/movieInfo/"+rating.getMovieId(),Movie.class);
	
//			 MovieSummary movieSummary=webClient_builder.build().get().uri(info_service+rating.getMovieId())
//			       .retrieve().bodyToMono(MovieSummary.class).block();
//			 
//			 
//			return new MovieCatalog(movieSummary.getOriginal_title(),movieSummary.getOverview(),rating.getRating());
//			
//		}).collect(Collectors.toList());
		
//		
//		 MovieSummary movieSummary=webClient_builder.build().get().uri(info_service+rating.getMovieId())
//			       .retrieve().bodyToMono(MovieSummary.class).block();
//		 
//		return new MovieCatalog(movieSummary.getOriginal_title(),movieSummary.getOverview(),rating.getRating());
//		
//	}
//	
//	public MovieCatalog getfallBackMovieCatalog(MovieRating rating)
//	{
//		return new MovieCatalog("Francisca Uduranga","Very bad",rating.getRating());
//	}

//	public List<MovieCatalog> getfallBackMovieCatalog(int userId)
//	{
//		
//		return Arrays.asList(
//				new MovieCatalog("Los olvidados",overView1,6.9),
//				new MovieCatalog("Het Schnitzelparadijs",overView2,7.3),
//				new MovieCatalog("Fall Back","Circuit Breaker Pattern triped",2.0)
//				);
//	}
	
//	public void discoveryClient()
//	{
//		List<ServiceInstance> instances=discoverClient.getInstances("rating-data-service");
//	
//	        
//	        for(ServiceInstance instance:instances)
//	        {
//	        	 System.out.println(instance.getServiceId());
//	        	 System.out.println(instance.getHost());
//	        	 System.out.println(instance.getPort());
//	        	 System.out.println(instance.getUri());
//	        }
//	       
//	}
}
